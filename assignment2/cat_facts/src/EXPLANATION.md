CatFactsRetriever depends on an external api.
If i want to test CatFactsRetriever, i must stub the responses of the external api.
For that the HttpUtil::get method must be an instance method, thus i refactored it to make it testable.
