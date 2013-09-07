---
layout: post
category : tutorial
tagline: "Your First Scala Typeclass: Semigroup"
tags : [semigroup, tutorial]
---
{% include JB/setup %}

A typeclass is an interface that a type can implement. If the type implements the interface, it will often inherit methods other than those in the interface. These inherited methods give your type additional capabilities.

Imagine that there's a typeclass for addition. If you provide the implementation for its `add2` function, then you might automatically get an `add3` function that is implemented for every class as `add2(add2(a, b), c)`.

In fact, this "addition typeclass" already exists as a fundamental typeclass called [Semigroup](http://scalaz.github.io/scalaz/scalaz-2.10-7.0.3/doc/index.html#scalaz.Semigroup) that's a more general form of addition. Rather than `add2`, the operation you have to implement to inherit from Semigroup is `append`. Like "Semigroup", `append` is not a name that describes what's going on very well! In simple terms, a Semigroup is a type whose instances can be combined, returning another instance of the same type. The implementation of Semigroup requires that performing `append` must satisfy the associative law. In other words, `append(a, append(b, c))` has to give the same result as `append(append(a, b), c)`.

So you can imagine that familiar things like addition, multiplication, and building a list are all satisfactory `append` operations.

Let's try extending Semigroup with an object called AddMore.

{% highlight scala %}
object AddMore extends Semigroup[Int] {
  def append(a: Int, b: => Int): Int = a + b + 1
}

println(AddMore.append(1, 2)) // prints 4
println(AddMore.semigroupLaw.associative(1, 2, 3)) // prints true
println(AddMore.apply.map(1)(x => 3)) // prints 1 (surprise!)
{% endhighlight %}

So AddMore's `append` function adds two numbers and adds one to the sum. By implementing `append`, we get a few functions for free. Most are not very interesting because the Semigroup laws don't specify enough for much functionality. One more definition that we'll add to Semigroup later will help with that.

You can see that implementing a Scalaz typeclass gets you a typeclass member that has "Law" in the name that helps you test whether your implementation satisfies that typeclass's laws. Semigroup also gives you an implementation of the Apply typeclass, but as you can see, it's kind of broken. Usually, implementing Apply gives you a nice implementation of `map`, but this one ignores the function you pass it! That's a consequence of how little you get from implementing a typeclass with nothing but `append`.

Typeclasses with more features can be built by inheriting from multiple typeclasses (or by inheriting from a typeclass that inherits from multiple typeclasses itself).
