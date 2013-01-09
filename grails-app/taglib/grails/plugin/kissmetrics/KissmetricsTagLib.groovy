package grails.plugin.kissmetrics

import grails.util.Environment

class KissmetricsTagLib {

    static namespace = 'kissmetrics'

    /**
     * Initialize Kissmetrics
     *
     */
    def initJS = { attrs ->
        if (enabled) {
            Map model = [
                    apiKey: config.apiKey
            ]
            out << render(template: '/tags/init-js', model: model, plugin: 'kissmetrics')
        }
    }

    /**
     * Alias identity
     *
     * @attr id REQUIRED
     * @attr to REQUIRED
     */

    def alias = { attrs ->
        if (enabled) {
            out << render(template: '/tags/alias', model: attrs, plugin: 'kissmetrics')
        }
    }

    /**
     * Identitify current user
     *
     * @attr id REQUIRED
     */

    def identify = { attrs ->
        if (enabled) {
            out << render(template: '/tags/identify', model: attrs, plugin: 'kissmetrics')
        }
    }

    /**
     * Record event
     *
     * @attr event REQUIRED
     * @attr properties
     */

    def record = { attrs ->
        if (enabled) {
            out << render(template: '/tags/record', model: attrs, plugin: 'kissmetrics')
        }
    }

    /**
     * Set properties
     *
     * @attr properties REQUIRED
     */

    def set = { attrs ->
        if (enabled) {
            out << render(template: '/tags/set', model: attrs, plugin: 'kissmetrics')
        }
    }

    // PRIVATE

    private def getConfig() {
        grailsApplication.config.grails?.plugin?.kissmetrics
    }

    private boolean isEnabled() {
        boolean configEnabled = false
        if (config.apiKey) {
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
