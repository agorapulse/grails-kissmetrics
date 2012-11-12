grails.project.work.dir = 'target'
grails.project.source.level = 1.6

grails.project.dependency.resolution = {
    inherits 'global'
    log 'warn'
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        compile('org.codehaus.groovy.modules.http-builder:http-builder:0.6') {
            excludes "commons-logging", "xml-apis", "groovy"
        }
    }
    plugins {
        build(':release:2.0.4', ':rest-client-builder:1.0.2') {
            export = false
        }
        runtime ':resources:1.2.RC2'
        test(':spock:0.7') {
            export = false
        }
    }
}
