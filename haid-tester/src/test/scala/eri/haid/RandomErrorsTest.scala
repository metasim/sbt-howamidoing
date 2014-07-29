package eri.haid
import scala.util.Random

class RandomErrorsTest extends HaidTestSpec {
    describe("something that has random errors") {
        it("might have an error 1") {
            assert(Random.nextBoolean)
        }

        it("might have an error 2") {
            assert(Random.nextBoolean)
        }

        it("might have an error 3") {
            assert(Random.nextBoolean)
        }

        it("might have an error 4") {
            assert(Random.nextBoolean)
        }

        it("might have an error 5") {
            assert(Random.nextBoolean)
        }

        it("might have an error 6") {
            assert(Random.nextBoolean)
        }
    }
}
