# jdbc-sql-rest
A primitive REST Api wrapper for SQL calls to a JDBC compliant database.

###Configuration & Setup
1. Fill out the required config details in src/main/resources/jdbc.config - this example is pre-populated for use with redshift
2. Make sure the JDBC java depedency you require is either in the classpath (mvn dependency or otherwise).

This example is configured to query an Amazon Redshift cluster.  You will notice that the Redshift JDBC driver is included, and referenced in the pom.  If you reference a different driver class in your config make sure the driver is included in your classpath otherwise it will stack trace.

###Port
The app binds to port 2511 by default.  This can be changed in Bootstrap.java, or when im less lazy I will make it configurable.

###Docker
If you have docker installed you can launch the application very easily

1. docker built -t jamescross91/sql-rest .
2. docker run -it -p 2511:2511 jamescross91/sql-rest

###Endpoints
There is a single REST endpoint: /query.  It expects a JSON body like this:

```
{
    "sql": "select * from pcap limit 10";
}
```

###Example query/response

####Query
```
curl -H "Content-Type: application/json" -X POST -d '{"sql":"select * from pcap limit 1"}' http://localhost:2511/query
```

####Response
```
[
  {
    "field1": "blah",
    "field2": "blah",
    "field3": "151021-mall",
    "field4": 64,
    "field5": 1445429544.715729
  }
]
```
