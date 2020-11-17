# SpringBootDataGeodeFindByIdError
I'm getting weird data back from Geode 1.13 when using spring data for geode findById()

This is proof of concept code

The logic is implemented in MyRunner.java

The class is simple, the data is stored correctly in Geode 1.13

The region is created like this:
gfsh> create region --name=TestRegion --type=PARTITION_REDUNDANT_PERSISTENT --recovery-delay=10000 --disk-store=DataPersistence --enable-statistics=true --eviction-action=overflow-to-disk --compressor='org.apache.geode.compression.SnappyCompressor' --redundant-copies=1

Issue: findById() returns the wrong data while findAll() works fine





