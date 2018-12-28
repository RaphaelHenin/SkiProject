# SkiProject - RHN & PTS
## Requirement :
Add the jgoodies-common-1.8.0.jar in the classpath. (Run Configurations -> Classpath tab -> Add external Jar)

## Subject
In anticipation of the upcoming winter, Mr. Snow, director of a major ski resort, is looking to optimize
the management of his company while making the ski area more attractive to tourists.
Passionate about new technologies, Mr. Snow shares his idea of installing interactive terminals
in the queues of the lifts and in various points of the resort allowing skiers to
select his itinerary. In particular, he would like to inform them how to get there as quickly as possible
possible to any other point of the station.
The station can thus be represented as an oriented graph where the vertices represent the points
of the resort and the edges represent the slopes, lifts and Bus line.

The ski resort is representing by two csv file :
- vertices.csv list all the vertices of the ski resort with its id, name and altitude.
- relation_vertices.csv list all the relations between each vertices. We can translate these relation by edges for graph representation. This file shows the name of the edge, the type of lift or the ski slopes difficulty, source vertice, destination vertice, and the altitude difference between the source and the destination vertice (the weigth of the edge).

## UML Diagram
![](Image/UML_SkiSpotting.png?raw=true)
![](http://image.noelshack.com/fichiers/2018/52/5/1546033509-uml-skispotting.png)
