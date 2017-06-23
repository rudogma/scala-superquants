package superquants

trait Operations {
  type Operation
  type Divide <: Operation
  type Multiply <: Operation
  type Plus <: Operation
  type Minus <: Operation
}
