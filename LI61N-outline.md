# Outline and resources #

## Week 1
* Course introduction
  * [Evaluation](https://github.com/isel-leic-daw/1819v-public/wiki/evaluation).
  * [Resources](https://github.com/isel-leic-daw/1819v-public/wiki/resources).
* The concept and motivation for Web APIs (or HTTP APIs)
  * [Web APIs](https://github.com/isel-leic-daw/1819v-public/wiki/Web-APIs).
  * [Chapter 2](https://www.oreilly.com/library/view/designing-evolvable-web/9781449337919/ch02.html).
* The [Architecture of the World Wide Web](https://www.w3.org/TR/webarch/)
  * [Chapter 1](https://www.oreilly.com/library/view/designing-evolvable-web/9781449337919/ch01.html).

* Intro to the HTTP protocol (see next week).

## Week 2

* No class on Wednesday due to the teacher's participation in [NDC Porto](https://ndcporto.com).

* The HTTP protocol
  * Request-response message exchange semantics and pattern.
  * [Chapter 1](https://www.oreilly.com/library/view/designing-evolvable-web/9781449337919/ch01.html).
  * Request methods [semantics](https://tools.ietf.org/html/rfc7231#section-4.3) and [properties](https://tools.ietf.org/html/rfc7231#section-4.2).
  * Response status code [semantics](https://tools.ietf.org/html/rfc7231#section-6).

## Week 3
* The HTTP protocol
  * The [HTTP bis specifications](https://tools.ietf.org/wg/httpbis/) 
  * [Intermediaries](https://tools.ietf.org/html/rfc7230#section-2.3): proxies, gateways, transparent intermediaries, in-proc intermediaries (e.g. NodeJS middleware).
  * [Messages and connections](https://tools.ietf.org/html/rfc7230#section-6).
  * Headers
    * Extensibility via request and response headers (e.g. `Accept` for content-negotiation).
    * Payload metadata (e.g. `Content-Type` and `Content-Lenght`).

* Error representation and how to represent insuccess.
  * ["How to fail in HTTP APIs"](https://github.com/isel-leic-daw/1819v-public/wiki/How-to-fail-in-HTTP-APIs).
  * The [`application/problem+json`](https://tools.ietf.org/html/rfc7807) media-type.
  * ["How to Think About HTTP Status Codes"](https://www.mnot.net/blog/2017/05/11/status_codes).

* The RPC (Remote Procedure Calls) model and the HTTP model.
  * ["HTTP is not RPC"](https://www.ics.uci.edu/~fielding/pubs/dissertation/evaluation.htm#sec_6_5_2).

* Introduction to representation formats, evolvability, and hypermedia.

## Week 4

* The link concept and the [Web Linking](https://tools.ietf.org/html/rfc8288) specification.
  * Required attributes and [link relations](https://www.iana.org/assignments/link-relations/link-relations.xhtml).
  * Example: the `search` link relation and the [Open Search](http://www.opensearch.org/Home) specification.
* The [Hypermedia Application Language (HAL)](http://stateless.co/hal_specification.html).

* The [Siren](https://github.com/kevinswiber/siren) hypermedia specification.

## Week 5
* IoC and DI, DI containers, Spring, Spring MVC, Spring Boot.
  * [Spring IoC and DI container](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans). Skip over the XML configuration metadata, since we will not be using it. Focus on the Java based configuration, including annotations:
  * [Configuration classes and factory methods](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-java).
  * [Annotation based configuration](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-annotation-config).
  * [Classpath scanning](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-classpath-scanning).
  * [Spring Web MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
    * Focus on the architectural elements, namely controllers and pipeline. The configuration will be done differently because we will be using [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-developing-web-applications).
  * [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/).
  * [Spring Initializr](https://start.spring.io).

## Week 6
* [Exception handling](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-exceptionhandlers)
* [The OpenAPI specification](https://www.openapis.org)
  * [A Visual Guide to What's New in ~~Swagger~~ OpenAPI 3.0.](https://blog.readme.io/an-example-filled-guide-to-swagger-3-2/)
* [API Explorer](https://github.com/sky-uk/api-explorer)
* [schema.org](http://schema.org)
* [Podcast feed example](http://feeds.feedburner.com/se-radio?format=xml)

## Week 7
* ES6 - promises, classes, destructuring, modules, transpilation, fetch.
  * [TC39 standardisation process](http://2ality.com/2015/11/tc39-process.html)
  * [TC39 proposals](https://github.com/tc39/proposals/blob/master/finished-proposals.md)
* [Jest](https://facebook.github.io/jest/)
* [StandardJS](https://standardjs.com/)
* [Exploring JS](http://exploringjs.com/)
  * See: restructuring, spread and rest operators, string templates, classes, promises, and async-await 
* [Using `fetch`](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch)
* Modules
  * [CommonJS Modules](http://www.commonjs.org/specs/modules/1.0/)
  * ["Module Patterns"](https://leanpub.com/modulepatterns)
* [Examples](https://github.com/isel-leic-daw/1819v-public/tree/master/LI61N/js/es-next-aula)

## Week 8
* Introduction to [React](https://reactjs.org)
  * UI definition as a function from a model to an element tree.
  * [Virtual DOM](https://reactjs.org/docs/faq-internals.html).
  * Elements and components
  * See [`https://reactjs.org/docs/getting-started.html`](https://reactjs.org/docs/getting-started.html)
  * See [React as a UI Runtime](https://overreacted.io/react-as-a-ui-runtime/)

## Week 9
* React - component lifecycle, components vs. instance vs. elements, application state management, examples.
* [React Components, Elements, and Instances](https://reactjs.org/blog/2015/12/18/react-components-elements-and-instances.html).
* [Update on Async Rendering](https://reactjs.org/blog/2018/03/27/update-on-async-rendering.html) - contains usage examples for the "new" lifecycle methods.
* [Glossary of React terms](https://reactjs.org/docs/glossary.html).

## Week 10
* [Thinking in React](https://reactjs.org/docs/thinking-in-react.html)

## Week 11

## Week 12

## Week 13

## Week 14

## Week 15
