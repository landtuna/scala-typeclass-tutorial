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

Let's try extending Semigroup with a class called AddMore.

{% highlight scala %}
case class AddMore(a) extends Semigroup
object AddMore {
  def append(f1: AddMore, f2: AddMore): AddMore = f1.a + f2.a + 1
}
{% endhighlight %}

Typeclasses with more features can be built by inheriting from multiple typeclasses.
