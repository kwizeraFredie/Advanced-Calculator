const display = document.getElementById("display");

function appendValue(value) {
  display.value += value;
}

function clearDisplay() {
  display.value = "";
}

function backspace() {
  display.value = display.value.slice(0, -1);
}

function calculate() {
  try {
    let expr = display.value
      .replace(/÷/g, "/")
      .replace(/×/g, "*")
      .replace(/\^/g, "**"); // power operator

    display.value = eval(expr);
  } catch {
    display.value = "Error";
  }
}

function applyFunction(func) {
  try {
    let val = eval(display.value);
    switch (func) {
      case "sin": display.value = Math.sin(val * Math.PI / 180); break;
      case "cos": display.value = Math.cos(val * Math.PI / 180); break;
      case "tan": display.value = Math.tan(val * Math.PI / 180); break;
      case "sqrt": display.value = Math.sqrt(val); break;
      case "log": display.value = Math.log10(val); break;
      case "ln": display.value = Math.log(val); break;
    }
  } catch {
    display.value = "Error";
  }
}

document.querySelectorAll(".btn-calc").forEach(btn => {
  btn.addEventListener("click", e => {
    const value = e.currentTarget.getAttribute("data-value");
    const action = e.currentTarget.getAttribute("data-action");
    const func = e.currentTarget.getAttribute("data-func");

    if (value) {
      appendValue(value);
    } else if (action) {
      if (action === "clear") clearDisplay();
      if (action === "backspace") backspace();
      if (action === "calculate") calculate();
      if (action === "func") applyFunction(func);
    }
  });
});
