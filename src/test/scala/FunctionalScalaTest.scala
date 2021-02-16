import org.scalatest.{FunSpec, Matchers}

class FunctionalScalaTest extends FunSpec with Matchers {

  it("shouldPassTest") {
    true shouldBe true
  }

  ignore("shouldFailTest") {
    true shouldBe false
  }
}
