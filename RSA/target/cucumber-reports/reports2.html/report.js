$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/features/placeValidations.feature");
formatter.feature({
  "name": "Validating Place API\u0027s",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Verify if the place is being Successfully added using AddPlaceAPI",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Add Place Payload with \"\u003cname\u003e\" \"\u003clanguage\u003e\" \"\u003caddress\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "name": "user calls \"addPlace\" with \"post\" http request",
  "keyword": "When "
});
formatter.step({
  "name": "the API call got success with a status code 200",
  "keyword": "Then "
});
formatter.step({
  "name": "\"status\" in response body is \"OK\"",
  "keyword": "And "
});
formatter.step({
  "name": "verify the place_id created maps to \"\u003cname\u003e\" using \"getPlace\"",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "name",
        "language",
        "address"
      ]
    },
    {
      "cells": [
        "sana",
        "English",
        "dubai, vivekanandar theru"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Verify if the place is being Successfully added using AddPlaceAPI",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Add Place Payload with \"sana\" \"English\" \"dubai, vivekanandar theru\"",
  "keyword": "Given "
});
formatter.match({
  "location": "AddPlaceApi.add_place_payload(String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user calls \"addPlace\" with \"post\" http request",
  "keyword": "When "
});
formatter.match({
  "location": "AddPlaceApi.user_calls_with_http_request(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the API call got success with a status code 200",
  "keyword": "Then "
});
formatter.match({
  "location": "AddPlaceApi.the_api_call_got_success_with_a_status_code_200(Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "\"status\" in response body is \"OK\"",
  "keyword": "And "
});
formatter.match({
  "location": "AddPlaceApi.in_response_body_is(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "verify the place_id created maps to \"sana\" using \"getPlace\"",
  "keyword": "And "
});
formatter.match({
  "location": "AddPlaceApi.verify_the_place_id_created_maps_to_using(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify if the place is being Successfully deleted using deletePlace",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "deletePlace Payload with place_id",
  "keyword": "Given "
});
formatter.match({
  "location": "AddPlaceApi.delete_place_payload_with_place_id()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user calls \"deletePlace\" with \"delete\" http request",
  "keyword": "When "
});
formatter.match({
  "location": "AddPlaceApi.user_calls_with_http_request(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the API call got success with a status code 200",
  "keyword": "Then "
});
formatter.match({
  "location": "AddPlaceApi.the_api_call_got_success_with_a_status_code_200(Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "\"status\" in response body is \"OK\"",
  "keyword": "And "
});
formatter.match({
  "location": "AddPlaceApi.in_response_body_is(String,String)"
});
formatter.result({
  "status": "passed"
});
});