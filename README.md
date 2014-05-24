correlation-id-sync
===================

This is a simple Spring boot-based application that demonstrates how request correlationIds could be generated and managed in order to correlate an initial request through a distributed (SOA/microservices) application.

Please note that this version relies on ThreadLocal, and so will only work if you are handling requests synchronously in the initiating thread. As soon as you use a DeferredResult, Future or Callable, this reliance on ThreadLocal will not propogate the correlationIds unless they are manually passed as method parameters (or some similiar approach)

Disclaimer: This is just an experiment and toy-example! I'm not suggesting this code is production-ready! :-)
