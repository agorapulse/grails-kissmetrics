grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
    inherits 'global'
    log 'warn'
    repositories {
        grailsCentral()
        mavenCentral()
    }
    dependencies {
        compile('org.codehaus.groovy.modules.http-builder:http-builder:0.6') {
            excludes "commons-logging", "xml-apis", "groovy"
        }
    }
    plugins {
        build(':release:latest.integration') {
            export = false
        }
        //compile ':cookie-session:0.1.2'
        //runtime ':kissmetrics:0.1.0'
        runtime ':resources:1.2.RC2'
        test ':spock:0.6'
    }
}