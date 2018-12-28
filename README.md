# SkiProject - RHN & PTS

## To Improve :
- Design of the GUI
- Correct time format
- Implement flow management

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

## Poster
Here is what we would like to achieve, both visually (Spotting Terminal) and in terms of functionality (The different wood boards at the bottom right)
![](Image/Poster_PTS_IBO.png?raw=true)

## Technical choice
As we have an oriented valuated graph with no negative weight on the edges, we have use Dijsktra algorithm to propose the shortest way to reach a destination.

### How the graph is built
The graph is composed of two List :
- List of Node
- List of Edge

#### Node
A node represent a summit of the Ski resort so the attributes of a node are :
- Id
- Name
- Altitude

#### Edge
An edge represent a Ski Slope, a Ski lift or a Bus line of the Ski resort.
Technically, it's an abstract class with the following attributes :
- Name (String)
- Source (Node) : The start
- Destination (Node) : The destination
- Time (Double) : The weight of the edge
- TypeOfTransport (String) : We have different type of transport,ski slopes, ski lifts and bus line. In inputs data we have can identity if it's a ski slope, a ski lift or a bus line by following the list below (he acronyms correspond to the French names) :

###### Ski slope :
- Green Slope (V)
- Blue Slope (B)
- Red Slope (R)
- Black Slope (N)
- Track of kilometres launched (KL)
- Snowpark (SURF)

###### Ski lift :
- Teleferic (TPH)
- Cable car (TC)
- Disengageable chairlift (TSD)
- Chairlift (TS)
- Ski lift (TK)

###### Bus line:
- Bus line (BUS)

For each type of transport, a class is created. These classes inherit Edge class.
Each child class has his own manner to calcul the weight of the edge. This is why the Edge class is abstract. Each child class will override the calculTime method.


#### Level Adapted route

##### User
To represent the skier the user class is created with the following attributes :
- Name
- Last name
- Level (Expert, Intermediate or Beginner)

The algorithm take into account the level of the skier. The rules are simple :
- If the skier is beginner, he can only pass throught Blue Slope and Green Slope.
- If the skier is intermediate, he can only pass throught Blue Slope, Green Slope and Red Slope.
- If the skier is expert, he can pass throught all the Ski slope.

So technically, in the dijkstra algorithm we need to get the level (so the his attribute) of the current skier and avoid the Ski slope based on the rules.

#### GUI
This how the GUI actually looks like :
![](Image/Capture_GUI.png?raw=true)

## UML Diagram
![](Image/UML_SkiSpotting.png?raw=true)
