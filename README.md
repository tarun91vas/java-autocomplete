## Java Autocomplete
An autocomplete engine in java using suffix trie and inverted index.

#### Build

`./gradlew clean build`


#### Run

`java -jar ./build/libs/java-autocomplete-0.0.1.jar`


#### Request

GET /search/{query}

#### Response

```
{
  "time_in_millis": "19.573423",
  "data": [
    {
      "value": "abc",
      "score": 6.25,
      "substring_start_index": 0,
      "fuzzy": 0
    },
    {
      "value": "abca",
      "score": 5.0,
      "substring_start_index": 0,
      "fuzzy": 0
    },
    {
      "value": "abco",
      "score": 5.0,
      "substring_start_index": 0,
      "fuzzy": 0
    },
	.
	.
	.
  ]
}
```
