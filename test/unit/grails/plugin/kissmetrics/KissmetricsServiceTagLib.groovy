package grails.plugin.kissmetrics

import grails.test.mixin.TestFor
import grails.util.Environment
import spock.lang.Specification

@TestFor(KissmetricsService)
class KissmetricsServiceSpec extends Specification {

    def "should be enabled for PRODUCTION by default"() {
        when:
        setEnvironment(Environment.PRODUCTION)

        then:
        assert service.enabled == true
    }

    def "should be disabled for NON-PRODUCTION by default" () {
        when:
        setEnvironment(Environment.CUSTOM)

        then:
        assert service.enabled == false
    }

    def "should be enabled when config enables Kissmetrics" () {
        when:
        enableKissmetrics(true)

        then:
        assert service.enabled == true
    }

    def "should be disabled for PRODUCTION when config disables Kissmetrics" () {
        when:
        setEnvironment(Environment.PRODUCTION)
        enableKissmetrics(false)

        then:
        assert service.enabled == false
    }

    private setEnvironment(environment) {
        Environment.metaClass.static.getCurrent = { ->
            return environment
        }
    }

    private enableKissmetrics(boolean value){
        service.grailsApplication.config = [
                grails: [
                        plugin: [
                                kissmetrics: [
                                        enabled: value
                                ]
                        ]
                ]
        ]
    }
}
