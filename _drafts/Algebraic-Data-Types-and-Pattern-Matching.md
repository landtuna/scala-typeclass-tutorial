---
layout: post
category : tutorial
tagline: "Algebraic Data Types and Pattern Matching"
tags : [intro, tutorial]
---
{% include JB/setup %}

Before getting started with typeclasses, it's worth taking some time to understand two features that make typeclasses rich and easy to use.

### Algebraic Data Types ###
"Algebraic Data Types" sounds a lot more complex than it is. An algebraic data type (shortened to ADT below) is just a set of data types grouped together with AND and OR. So if I made a data type called CityState that had both a city name and a state name inside, that would be an ADT that had two strings ANDed together.

{% highlight scala %}
case class CityState(city: String, state: String)
{% endhighlight %}

Likewise, if I had an ADT called Municipality that could be either a string for a city name or a string for a town name, that would be an ADT that had two strings ORed together. Why separate cities and towns if they're both strings? You might want to *usually* work with municipalities but occasionally treat cities and towns differently. ADTs can help group similar and dissimilar items.

{% highlight scala %}
sealed trait Municipality
case class City(name: String) extends Municipality
case class Town(name: String) extends Municipality
{% endhighlight %}

Now, there are more ways to combine data types than AND and OR, but if you keep the above explanation in mind whenever someone talks about algebraic data types, you'll often find that it's plenty.

My favorite article about algebraic data types is [The Algebra of Algebraic Data Types](http://chris-taylor.github.io/blog/2013/02/10/the-algebra-of-algebraic-data-types/). One thing you'll notice is that the term for types that are ANDed together is a *product type*, and the term for types that are ORed together is a *sum type*.