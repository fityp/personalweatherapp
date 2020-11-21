<!DOCTYPE html>
<html>
<head>
<title>Table with database</title>
<style>
table {
border-collapse: collapse;
width: 100%;
color: #588c7e;
font-family: monospace;
font-size: 25px;
text-align: left;
}
th {
background-color: #588c7e;
color: white;
}
tr:nth-child(even) {background-color: #f2f2f2}
</style>
</head>
<body>
<table>
<tr>
<th>StationID</th>
<th>DateGethered</th>
<th>WH65</th>
<th>Temperature</th>
<th>Humidity</th>
<th>WindDirection</th>
<th>WindSpeed</th>
<th>WindGust</th>
<th>MaxDailyGust</th>
<th>Radiation</th>
<th>UV</th>
<th>RainRate</th>
<th>RainingEvent</th>
<th>HourlyRaining</th>
<th>DailyRaining</th>
<th>WeeklyRaining</th>
<th>MonthlyRaining</th>
<th>YearlyRaining</th>
<th>TotalRaining</th>
<th>TemperatureInside</th>
<th>HumidityInside</th>
<th>Baromrelin</th>
<th>Baromabsin</th>
</tr>
<?php

$username = 'dbok';
$password = 'bc6919ax';
$database = 'pwsdb';

$conn = mysqli_connect("127.0.0.1:3306", $username, $password, $database);
// Check connection
if ($conn->connect_error) {
die("Connection failed: " . $conn->connect_error);
}
$sql = "SELECT * FROM StationData";

$result = $conn->query($sql);
if ($result->num_rows > 0) {
// output data of each row
while($row = $result->fetch_assoc()) {

echo "<tr>

<td>" . $row["station_id"] . "</td>
<td>" . $row['gethered_date'] . "</td>
<td>" . $row['wh65batt'] . "</td>
<td>" . $row['tempf'] . "</td>
<td>" . $row['humidity'] . "</td>
<td>" . $row['Winddir'] . "</td>
<td>" . $row['windspeedmph'] . "</td>
<td>" . $row['windgustmph'] . "</td>
<td>" . $row['maxdailygust'] . "</td>
<td>" . $row['solarradiation'] . "</td>
<td>" . $row['uv'] . "</td>
<td>" . $row['rainratein'] . "</td>
<td>" . $row['eventrainin'] . "</td>
<td>" . $row['hourlyrainin'] . "</td>
<td>" . $row['dailyrainin'] . "</td>
<td>" . $row['weeklyrainin'] . "</td>
<td>" . $row['monthlyrainin'] . "</td>
<td>" . $row['yearlyrainin'] . "</td>
<td>" . $row['totalrainin'] . "</td>
<td>" . $row['tempinf'] . "</td>
<td>" . $row['humidityin'] . "</td>
<td>" . $row['baromrelin'] . "</td>
<td>" . $row['baromabsin'] . "</td>

</tr>";

}
echo "</table>";
} else { echo "0 results"; }
$conn->close();
?>
</table>
</body>
</html>

