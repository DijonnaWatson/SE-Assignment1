# SE-Assignment1

API's chosen: Finding all the factors of the given input, Finding the factorial of a 2-digit number below 50, Even Fibonacci Numbers
NUM OF THREADS USED: 2

![API Diagram](https://github.com/DijonnaWatson/SE-Assignment1/blob/Redone-API-A%232/FINDING%20FACTORS%20API%20DIAGRAM.pdf)

#Benchmark Test

In our FactorComputeEngineImpl the method checked each factor from 1 - inputnumber. We identified this line of code as a CPU bottleneck and chose to improve it.
We created an optimized implementation called FactorComputeEngineOptimizedImpl. The loop now only checks up to the sqaure root of the input number. By looping only up to the square root of the input number, 
we reduced the number of checks we need to make by a factor of about sqrt(n).

Original Implementation Average Time: 1430960 ns
Optimized Implementation Average Time: 41391 ns
Performance Improvement: 97.10746631631912%

Here is the link to the benchmarktest: https://github.com/DijonnaWatson/SE-Assignment1/blob/c3ef8a4782f601535533c2f8946251d8506935f5/test/FactorComputeEngineBenchmarkTest.Java

