'use strict';

//була зроблена функція для обчислення податку з доходу

//для зчитування введення з клавіатури
const readline = require('node:readline');

//податок (24%)
const taxRate = 0.24;

class Either {
  #left = null;
  #right = null;

  constructor({ left = null, right = null }) {
    this.#left = left;
    this.#right = right;
  }

  static left(value) {
    return new Either({ left: value, right: null });
  }

  static right(value) {
    return new Either({ left: null, right: value });
  }

  get left() {
    return this.#left;
  }

  get right() {
    return this.#right;
  }

  isLeft() {
    return this.#left !== null;
  }

  isRight() {
    return this.#right !== null;
  }

  map(fn) {
    if (this.#right === null) return this;
    return Either.right(fn(this.#right));
  }

  match(leftFn, rightFn) {
    const isRight = this.#right !== null;
    return isRight ? rightFn(this.#right) : leftFn(this.#left);
  }
}

// Usage
/*
const success = Either.right(42);
const failure = Either.left(500);

const doubled = success.map((x) => x * 2);
console.log({ doubled: doubled.right });

const result = failure.match(
  (error) => `Failure: ${error}`,
  (value) => `Success: ${value}`,
);

console.log({ result });
*/



//функція для обчислення податку з доходу
function calculateTax(income) {
  if (isNaN(income) || income <= 0) {
    // Якщо ні то помилка (left)
    return Either.left('Invalid income amount');
  }

  const tax = income * taxRate;

  //якщо немає помилки (right)
  return Either.right({ tax, rate: taxRate });
}


//зчитування введення користувача
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});


//логіка взаємодії з користувачем
rl.question('Введіть ваш дохід: ', (input) => {
  const income = Number(input);

  //виклик функції розрахунку податку
  calculateTax(income)
  //знаки після коми при успіху (right)
    .map(({ tax, rate }) => ({
      tax: tax.toFixed(2),
      rate: (rate * 100).toFixed(0) + '%'
    }))
    //обробка результату
    .match(
      (err) => console.log('Помилка:', err),
      ({ tax, rate }) => {
        console.log('Податок становить:', tax, 'при ставці', rate);
        console.log('[LOG] Розрахунок виконано успішно.');
      }
    );

  rl.close();
});