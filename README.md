# ktor(basic)

## what?

ktor is "a framework for building asynchronous servers and clients in connected systems using the powerful Kotlin programming language"

(it's a set of client and server networking libraries written in kotlin)

my goal is to demonstrate a simple web api in ktor and spark your curiosity

## why?

there's an elegance to simplicity.

(and there is some rad stuff you can do thanks to kotlin)

## getting started

since ktor is made by jetbrains, there is an intellij plugin you can use
you can also go to start.ktor.io
or just throw stuff in a build.gradle file

you'll notice there are a few options, we'll talk through a few of them

Project SDK (java version)
Project (gradle, gradle w kotlin dsl, maven)
Gradle wrapper?
Embedded server (Netty recommended for general purpose)
Ktor version

Templating languages (server side rendering)

Features
- Static content (static html, css, js, images, whatever)
- Locations - type safe routing (sneak peak at the end)
- Metrics - expose basic metrics on routes and jvm via JMX or SLF4J
- Sessions for HTTP Sessions
- Compression for automatic compression of responses
- AutoHeadResponse provides automatic HTTP HEAD responses for GET endpoints
- CallLogging logs incoming requests
- ConditionHeaders obeys last modified headers instead of blindly providing a response each time
- CORS
- CachingHeaders sends cache information of responses to proxies/clients
- DataConversion - used by Locations
- DefaultHeaders - default headers to include with responses
- ForwardedHeaderSupport - parses reverse proxy headers to get information from original requests
- HSTS - enable HTTP strict transport security
- Status Pages - enable server to respond to thrown exceptions with generic errors
- Routing - define structured routes and handlers
- Webjars - allows you to package assets as part of your jar
- HttpsRedirect - automatic redirect to HTTP
- shutdown url - add an endpoint to shutdown the server
- websockets
- partialcontent - handle the Range headers

Authentication (various authentication schemes)

Content Negotiation (GSON or Jackson for JSON, Content-Type and Accept headers)

Sockets (unsecured and secured)

Skip the Client section

## A small sample app

a microservice for people and their vehicles.

