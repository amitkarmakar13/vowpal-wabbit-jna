vowpal-wabbit-jna
=================

A very simple Vowpal Wabbit connector for Java using JNA.

How to use
==========

Download and compile Vowpal Wabbit sources.

Place src/main/resources/libvw_simple_jna.cc inside VOWPAL_WABBIT_SOURCES_ROOT / vowpalwabbit directory.

Generate shared library file using,

<pre>g++ libvw_simple_jna.cc -o libvw_simple_jna.so --shared -fPIC</pre>

Set jna library path before using the connector,

<pre>
System.setProperty("jna.library.path", [ colon separated directory list containing all native shared libraries ]);
SimpleVowpalWabbitConnector connector = SimpleVowpalWabbitConnector.getInstance();
</pre>

See in.amitkarmakar.vowpalwabbit.jna.example.Example for example usage.

Blog
====

[http://blog.amitkarmakar.in/2014/08/jna-wrapper-for-vowpal-wabbit.html](http://blog.amitkarmakar.in/2014/08/jna-wrapper-for-vowpal-wabbit.html)