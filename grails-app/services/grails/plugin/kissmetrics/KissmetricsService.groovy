package grails.plugin.kissmetrics

import grails.util.Environment
import groovyx.net.http.AsyncHTTPBuilder
import groovyx.net.http.HTTPBuilder

import java.util.concurrent.Future

class KissmetricsService {

    static final String ROOT_URI = 'https://trk.kissmetrics.com'
    static transactional = false

    def grailsApplication

    private HTTPBuilder _http
    private AsyncHTTPBuilder _asyncHttp

    void alias(String identity, String alias) {
        assert identity
        assert alias
        if (enabled) http.get(path: '/a', query: buildQuery(identity) + [_n: alias])
    }

    Future aliasAsync(String identity, String aliasTo) {
        assert identity
        assert aliasTo
        if (enabled) asyncHttp.get(path: '/a', query: buildQuery(identity) + [_n: aliasTo])
    }

    def recordEvent(String identity, String eventName, Map properties = [:], int timestamp = 0) {
        assert identity
        assert eventName
        if (enabled) http.get(path: '/e', query: buildQuery(identity, timestamp) + [_n: eventName] + properties)
    }

    Future recordEventAsync(String identity, String eventName, Map properties = [:], int timestamp = 0) {
        assert identity
        assert eventName
        if (enabled) asyncHttp.get(path: '/e', query: buildQuery(identity, timestamp) + [_n: eventName] + properties)
    }

    def setProperties(String identity, Map properties = [:], int timestamp = 0) {
        assert identity
        if (enabled) {
            http.get(path: '/s', query: buildQuery(identity, timestamp) + properties)
        }
    }

    Future setPropertiesAsync(String identity, Map properties = [:], int timestamp = 0) {
        assert identity
        if (enabled) asyncHttp.get(path: '/s', query: buildQuery(identity, timestamp) + properties)
    }

    // PRIVATE

    private Map buildQuery(String identity, int timestamp = 0) {
        Map query = [
                _k: config.apiKey,
                _p: identity
        ]
        if (timestamp) {
            query + [
                    _t: timestamp,
                    _d: 1
            ]
        } else {
            query
        }
    }

    private def getConfig() {
        grailsApplication.config.grails?.plugin?.kissmetrics
    }

    private HTTPBuilder getHttp() {
        if (!_http) _http = new HTTPBuilder(ROOT_URI)
        _http
    }

    private AsyncHTTPBuilder getAsyncHttp() {
        if (!_asyncHttp) _asyncHttp = new AsyncHTTPBuilder(uri: ROOT_URI)
        _asyncHttp
    }

    private boolean isEnabled() {
        boolean configEnabled = false
        if (config) {
            // default enabled for PROD
            configEnabled = (Environment.current == Environment.PRODUCTION)

            // if config specified, use that instead
            if (config.containsKey('enabled')) {
                configEnabled = config.enabled
            }
        }
        configEnabled
    }

}