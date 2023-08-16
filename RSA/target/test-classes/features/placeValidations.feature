Feature: Validating Place API's

Scenario Outline: Verify if the place is being Successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "addPlace" with "post" http request
	Then the API call got success with a status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify the place_id created maps to "<name>" using "getPlace"
Examples:
		| name	   | language | address 				  |
		| sana     | English  | dubai, vivekanandar theru |
#		| asrt     | French   | chennai, tngar 			  |


Scenario: Verify if the place is being Successfully deleted using deletePlace
	Given deletePlace Payload with place_id
	When user calls "deletePlace" with "delete" http request
	Then the API call got success with a status code 200
	And "status" in response body is "OK"
	