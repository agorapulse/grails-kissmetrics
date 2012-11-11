package grails.plugin.kissmetrics

import grails.util.Environment
import spock.lang.Specification
import grails.test.mixin.TestFor

@TestFor(KissmetricsTagLib)
class KissmetricsTagLibSpec extends Specification {

    def "should be enabled for PRODUCTION by default"() {
        when:
        setEnvironment(Environment.PRODUCTION)

        then:
        assert tagLib.enabled == true
    }

    def "should be disabled for NON-PRODUCTION by default" () {
        when:
        setEnvironment(Environment.CUSTOM)

        then:
        assert tagLib.enabled == false
    }

    def "should be enabled when config enables Kissmetrics" () {
        when:
        enableKissmetrics(true)

        then:
        assert tagLib.enabled == true
    }

    def "should be disabled for PRODUCTION when config disables Kissmetrics" () {
        when:
        setEnvironment(Environment.PRODUCTION)
        enableKissmetrics(false)

        then:
        assert tagLib.enabled == false
    }

    private setEnvironment(environment) {
        Environment.metaClass.static.getCurrent = { ->
            return environment
        }
    }

    private enableKissmetrics(boolean value){
        tagLib.grailsApplication.config = [
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