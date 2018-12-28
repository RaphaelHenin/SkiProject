# SkiProject - RHN & PTS
Requirement :
Install the jar : https://mvnrepository.com/artifact/com.jgoodies/jgoodies-common/1.8.0

Help skiers reach a destination by using the different ski slopes and modes of transport available in a ski station.

In anticipation of the upcoming winter, Mr. Snow, director of a major ski resort, is looking to optimize
the management of his company while making the ski area more attractive to tourists.
Passionate about new technologies, Mr. Snow shares his idea of installing interactive terminals
in the queues of the lifts and in various points of the resort allowing skiers to
select his itinerary. In particular, he would like to inform them how to get there as quickly as possible
possible to any other point of the station.
The station can thus be represented as an oriented graph where the vertices represent the points
of the resort and the edges represent the slopes and lifts.

The ski resort is representing by two csv file :
- vertices.csv list all the vertices of the ski resort with its id, name and altitude.
- relation_vertices.csv list all the relations between each vertices. We can translate these relation by edges for graph representation. This file shows the name of the edge, the type of lift or the ski slopes difficulty, source vertice, destination vertice, and the altitude difference between the source and the destination vertice (the weigth of the edge).
