<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Charts</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }
        .header {
            margin-bottom: 20px;
            text-align: center;
        }
        .chart-container {
            width: 80%;
            max-width: 800px;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Employee Charts</h1>
    </div>
    <div class="chart-container">
        <h2>Daily Tasks/Hours (Pie Chart)</h2>
        <canvas id="dailyChart"></canvas>
    </div>
    <div class="chart-container">
        <h2>Weekly Tasks/Hours (Bar Chart)</h2>
        <canvas id="weeklyChart"></canvas>
    </div>
    <div class="chart-container">
        <h2>Monthly Tasks/Hours (Bar Chart)</h2>
        <canvas id="monthlyChart"></canvas>
    </div>
    <script>
        // Sample data for the charts, replace with dynamic data as needed
        const dailyData = {
            labels: ['Task A', 'Task B', 'Task C', 'Task D'],
            datasets: [{
                data: [10, 20, 30, 40],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0'],
            }]
        };

        const weeklyData = {
            labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4'],
            datasets: [{
                label: 'Hours',
                data: [40, 35, 50, 45],
                backgroundColor: '#36A2EB',
            }]
        };

        const monthlyData = {
            labels: ['January', 'February', 'March', 'April'],
            datasets: [{
                label: 'Hours',
                data: [160, 140, 180, 150],
                backgroundColor: '#FFCE56',
            }]
        };

        window.onload = function() {
            const dailyCtx = document.getElementById('dailyChart').getContext('2d');
            new Chart(dailyCtx, {
                type: 'pie',
                data: dailyData,
            });

            const weeklyCtx = document.getElementById('weeklyChart').getContext('2d');
            new Chart(weeklyCtx, {
                type: 'bar',
                data: weeklyData,
            });

            const monthlyCtx = document.getElementById('monthlyChart').getContext('2d');
            new Chart(monthlyCtx, {
                type: 'bar',
                data: monthlyData,
            });
        };
    </script>
</body>
</html>
