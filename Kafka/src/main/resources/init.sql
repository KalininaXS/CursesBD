create database measurement;

create table test
(
    timestamp DATETIME64,
    source    String,
    value     double
)
    engine = Kafka('localhost:9092', 'measurement_topic', 'measurements_group', 'JSONEachRow');