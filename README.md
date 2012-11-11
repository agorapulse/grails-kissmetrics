KISSmetrics Grails Plugin
=========================

# Introduction

The **KISSmetrics Plugin** allows your [Grails](http://grails.org) application to use [KISSmetrics](http://support.kissmetrics.com/apis) APIs.

It provides the following Grails artefacts:
* **KissmetricsService** - A server side client to call [KISSmetrics APIs](http://support.kissmetrics.com/apis/specifications).
* **KissmetricsTagLib** - A collection of tags to use [KISSmetrics Javascript Library](http://support.kissmetrics.com/apis/javascript) in your GSPs.


# Installation

Declare the plugin dependency in the BuildConfig.groovvy file, as shown here:

```groovy
grails.project.dependency.resolution = {
		inherits("global") { }
		log "info"
		repositories {
				//your repositories
		}
		dependencies {
				//your dependencies
		}
		plugins {
				//here go your plugin dependencies
				runtime ':kissmetrics:0.1.0'
		}
}
```


# Config

Create a [KISSmetrics](http://www.kissmetrics.com) account, in order to get your own apiKey.

Add your KISSmetrics credentials parameters to your _grails-app/conf/Config.groovy_:

```groovy
grails.plugin.kissmetrics.apiKey = {API_KEY}
```
By default the KISSmetrics will only be enabled for Production environments.  If you need it to be enabled for other environments, make sure that it is explicitly enabled in your configs.

```groovy
grails.plugin.kissmetrics.enabled = true
```


# Usage

## KissmetricsService

You can inject _kissmeticsService_ in any of your Grails artefacts (controllers, services...) in order to call KISSmetrics APIs.

```groovy
def kissmeticsService

// Alias identity
kissmetricsService.alias('bob@bob.com', 'bob')

// Record an event
kissmetricsService.recordEvent('bob@bob.com', 'Signed up')

// Record an event and set properties
kissmetricsService.recordEvent('bob@bob.com', 'Signed up', [plan: 'Pro', amount: 99.95])

// Record a past event and set properties
kissmetricsService.recordEvent('bob@bob.com', 'Signed up', [plan: 'Pro', amount: 99.95], (new Date() - 10).time)

// Set properties
kissmetricsService.setProperties('bob@bob.com', [gender: 'male'])
```

An *async* version is available for each methods (to delegate call to a thread pool and execute it asynchronously).

```groovy
// Alias identity
kissmetricsService.aliasAsync('bob', 'bob@bob.com')

// Record an event
kissmetricsService.recordEventAsync('bob@bob.com', 'Signed up')

// Set properties
kissmetricsService.setPropertiesAsync('bob@bob.com', [gender: 'male'])
```


## KissmetricsTagLib

To use [KISSmetrics Javascript Library](http://support.kissmetrics.com/apis/javascript), you must first initialize it in page header (most probably in you layout GSP).

```jsp
<!DOCTYPE html>
<html>
<head>
    <kissmetrics:initJS/>
```

Then, you can use [KISSmetrics Javascript Library](http://support.kissmetrics.com/apis/javascript) in your GSP views (to generate Javascript APIs call from client browser).

```jsp
<!-- Identity current user -->
<kissmetrics:identify id="bob@bob.com"/>

<!-- Record an event -->
<kissmetrics:record event="Signed Up"/>

<!-- Record an event and set properties -->
<kissmetrics:record event="Signed Up" properties="${[plan: 'Pro', amount: 99.95]}"/>

<!-- Set properties -->
<kissmetrics:set properties="${[gender: 'male']}"/>

<!-- Alias identity -->
<kissmetrics:alias id="bob@bob.com" to="bob"/>
```


# Latest releases

* 2012-11-28 **V0.1.0** : initial release

# Bugs

To report any bug, please use the project [Issues](http://github.com/benorama/grails-kissmetrics/issues) section on GitHub.


# Beta status

This is a **beta release**.
