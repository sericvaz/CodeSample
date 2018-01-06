<h4>Pseudo code:</h4>
<ol>SalesMessageProcessor calls the below services to process the input message:
<li> SalesMessageParse - This parses the input message and returns either of the two objects:
<ul><li>NewSaleRequest: Add new items to the SalesStore
<li>AdjustPriceRequest: Modify/Adjust the price of items already stored in the SaleStore.</li></li></ul>
<li> Based on the object returned from the above step, the RequestProcessingLookUp is invoked to get the service based on the request type.
<ul><li>If an instance of NewSaleRequest is returned, then AddNewSaleItemServiceImpl will be called and the SalesItem created from the request will be added to the SalesStore Items cache.</li>
<li>If an instance of AdjustPriceRequest is returned, then PriceAdjustmentServiceImpl will be called and the price will be adjusted for the items stored in the cache.</li></li></ul>
<li> After every 10 messages, SalesReportService is called to publish a report of all sales items stored.</li>
<li> After every 50 messages, SalesReportService is called to publish a report of all adjustments carried out.</li>
</ol>
<h4>Libraries/dependencies: </h4><ul>
<li>Apache Lucene: lucene-analyzers Version: 3.6.2 (Added to convert the plural form of a word to its singluar form. Used in class MessageParserImpl-> MessageParserUtil.</li>
<li>	Java: 1.8</li>
<li>Maven </li>
</ul>
<h4>Steps to Execute: </h4>
com.jp.app. TestMain: Execute this main class which will process sample records from test.txt file.




