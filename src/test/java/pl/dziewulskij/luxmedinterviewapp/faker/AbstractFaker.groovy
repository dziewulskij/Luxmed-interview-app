package pl.dziewulskij.luxmedinterviewapp.faker

import com.github.javafaker.Faker

class AbstractFaker {

    protected static def faker = new Faker(Locale.of("pl"))

}
