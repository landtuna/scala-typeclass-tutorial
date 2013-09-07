---
layout: post
category : tutorial
tagline: "Algebraic Data Types and Pattern Matching"
tags : [intro, tutorial]
---
{% include JB/setup %}

Before getting started with typeclasses, it's worth taking some time to understand a pair of related features that come up in some typeclasses.

### Algebraic Data Types ###
"Algebraic Data Types" sounds a lot more complex than it is. An algebraic data type (shortened to ADT below) is just a set of data types grouped together with AND and OR. So if I made a data type called CityState that had both a city name and a state name inside, that would be an ADT that had two strings ANDed together.

{% highlight scala %}
case class CityState(cityName: String, stateName: String)
{% endhighlight %}

Likewise, if I had an ADT called Municipality that could be either a string for a city name or a string for a town name, that would be an ADT that had two strings ORed together. Why separate cities and towns if they're both strings? You might want to *usually* work with municipalities but occasionally treat cities and towns differently. ADTs can help group similar and dissimilar items.

{% highlight scala %}
sealed trait Municipality
case class City(cityName: String) extends Municipality
case class Town(townName: String) extends Municipality
{% endhighlight %}

Now, there are more ways to combine data types than AND and OR, but if you keep the above explanation in mind whenever someone talks about algebraic data types, you'll often find that it's plenty.

My favorite article about algebraic data types is [The Algebra of Algebraic Data Types](http://chris-taylor.github.io/blog/2013/02/10/the-algebra-of-algebraic-data-types/). One thing you'll notice is that the term for types that are ANDed together is a *product type*, and the term for types that are ORed together is a *sum type*.

### Pattern Matching Algebraic Data Types ###
There's more to [pattern matching](http://scalabound.org/?p=292) in Scala than matching aginst ADTs, but ADTs are what we're interested in here.

An ADT that uses only AND is no different from the struct or class type that most other languages have. You can get to its fields by using a dot and the name of the field, like so:

{% highlight scala %}
val city = City("Springfield")
println(city.cityName)
{% endhighlight %}

But what do you do with an ADT that is made up of multiple case classes ORed together? You can't get the name out of a Municipality because the two case classes that it could be have different field names. (In practice, the case classes that are ORed together to make an ADT often have different types, too.)

To branch your code depending on which case class you have of an ADT, you can use pattern matching in Scala:

{% highlight scala %}
def decorateMunicipality(m: Municipality) = m match {
  case City(name) => s"City: $name"
  case Town(name) => s"Town: $name"
}
{% endhighlight %}
