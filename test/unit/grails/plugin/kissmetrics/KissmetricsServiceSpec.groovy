package grails.plugin.kissmetrics

import grails.test.mixin.TestFor
import grails.util.Environment
import spock.lang.Specification

@TestFor(KissmetricsService)
class KissmetricsServiceSpec extends Specification {

    def "should be disabled by default" () {
        assert service.enabled == false
    }

    def "should be disabled for PRODUCTION when no config is provided" () {
        when:
        setEnvironment(Environment.PRODUCTION)

        then:
        assert service.enabled == false
    }

    def "should be enabled for PRODUCTION by default"() {
        when:
        setEnvironment(Environment.PRODUCTION)
        buildConfig(apiKey: 'apiKey')

        then:
        assert service.enabled == true
    }

    def "should be disabled for NON-PRODUCTION by default" () {
        when:
        setEnvironment(Environment.CUSTOM)
        buildConfig(apiKey: 'apiKey')

        then:
        assert service.enabled == false
    }

    def "should be enabled when config enables Kissmetrics" () {
        when:
        buildConfig(apiKey: 'apiKey', enabled: true)

        then:
        assert service.enabled == true
    }

    def "should be disabled for PRODUCTION when config disables Kissmetrics" () {
        when:
        setEnvironment(Environment.PRODUCTION)
        buildConfig(apiKey: 'apiKey', enabled: false)

        then:
        assert service.enabled == false
    }

    // PRIVATE

    private buildConfig(Map config) {
        service.grailsApplication.config = [
                grails: [
                        plugin: [
                                kissmetrics: config
                        ]
                ]
        ]
    }

    private setEnvironment(environment) {
        Environment.metaClass.static.getCurrent = { ->
            return environment
        }
    }

}
