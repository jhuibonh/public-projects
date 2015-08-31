

var margin = {top: 20, right: 20, bottom: 30, left: 50},
    width = 1000 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;

var parseDate = d3.time.format("%Y-%m-%dT00:00:00Z").parse; 

var x = d3.time.scale()
    .range([0, width]);

var y = d3.scale.linear()
    .range([height, 0]);

var xAxis = d3.svg.axis()
    .scale(x)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("left");

var color = d3.scale.category10();

var line = d3.svg.line()
    .x(function(d) { return x(d.date); })
    .y(function(d) { return y(d.closePrice); });

var smaLine = d3.svg.line()
    .x(function(d) { return x(d.date); })
    .y(function(d) { return y(d.smaPrice); });


var svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")")

svg.append("rect")
    .attr("width", width+margin.right)
    .attr("height", height)
    .attr("class", "plot");

svg.append("clipPath")
    .attr("id", "clip")
    .append("rect")
    .attr("width", width+margin.right)
    .attr("height", height);

function zoomed() {
    svg.select(".x.axis").call(xAxis);
    svg.select(".y.axis").call(yAxis);
    svg.selectAll(".line")
        .attr("class", "line")
        .attr("d", line);
    svg.selectAll(".text")
        .attr("transform", function(d) { return "translate(" + x(d.date) + "," + y(d.closePrice) + ")"; });
    svg.selectAll(".SMAline")
        .attr("class", "SMAline")
        .attr("d", smaLine);
}

function fiveDaySMA(priceArray){
    var smaArray = [];
    var smaDict = {};
    var period = 5;
    var count = 0;
    var sum = 0
    var i = 0;
    while (priceArray[i]){
      if (count === period) {
         smaDict['smaPrice'] = sum/period
         smaArray.push(smaDict);
         smaDict = {};
         count = 0;
         sum = 0;
         smaDict['date'] = priceArray[i].date;
      }
      else if (count === 0){
        smaDict['date'] = priceArray[i].date;
      }
      sum += priceArray[i].closePrice;
      i++;
      count++;
    }
    return smaArray;
}

d3.json("api/getprice", function(data) {

  // array below is used to determine domain of x and y axis
  var allPriceList = [] 
  
  Object.keys(data).forEach(function(key) {
    data[key].forEach(function(stock) {
      stock.date = parseDate(stock.date);
      allPriceList.push(stock);
    });
  });

  x.domain(d3.extent(allPriceList, function(stock) { return stock.date; }));
  y.domain(d3.extent(allPriceList, function(stock) { return stock.closePrice; }));

  //set the scale extent after setting the domain of the x and y axis
  var zoom = d3.behavior.zoom()
    .x(x)
    .y(y)
    .scaleExtent([1, 10])
    .on("zoom", zoomed);

  d3.select("rect").call(zoom);

  svg.append("g")
      .attr("class", "x axis")
      .attr("transform", "translate(0," + height + ")")
      .call(xAxis)

  svg.append("g")
      .attr("class", "y axis")
      .call(yAxis)
     .append("text")
      .attr("transform", "rotate(-90)")
      .attr("y", 6)
      .attr("dy", ".71em")
      .style("text-anchor", "end")
      .text("close price");

  // loop through each stock and create a separate path for each stock
  Object.keys(data).forEach(function(key) {
    var smaArray = fiveDaySMA(data[key]);
    console.log(smaArray);
    console.log(data[key])
    // SMA path
    svg.append("path")
        .datum(smaArray)
        .attr("class", "SMAline")
        .attr("id", "SMA".concat(key))
        .attr("d", smaLine)
        .attr("clip-path", "url(#clip)")
        .style("stroke",function() {
            return "hsl(" + Math.random() * 360 + ",100%,50%)";
        });

    //stock close price path
    svg.append("path")
        .datum(data[key])
        .attr("class", "line")
        .attr("id", key)
        .attr("d", line)
        .attr("clip-path", "url(#clip)")
        .style("stroke",function() {
            return "hsl(" + Math.random() * 360 + ",100%,50%)";
        });

    // stock name text
    svg.append("text")
        .datum(data[key][data[key].length-1])
        .attr("class","text")
        .attr("y", -30)
        .attr("transform", function(d) { return "translate(" + x(d.date) + "," + y(d.closePrice) + ")"; })
        .attr("x", 40)
        .text(key.concat(" close price + SMA"));
  });

});
