<template>
  <div>
    <h1>Python Playground</h1>
    <h2>Skriv inn python koden din her:</h2>
    <textarea class="inputField" id="kodeinput"> </textarea>
    <button class="runButton" @click="kjorkode">Kjør koden</button>
    <textarea class="outputField" id="output" readonly> </textarea>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "HomeView",
  methods: {
    async kjorkode() {
      var kode = document.getElementById("kodeinput").value;
      var output = document.getElementById("output");
      output.value = "Compiling...";
      axios
        .post(`http://localhost:8080/`, null, {
          params: {
            script: kode,
          },
        })
        .then(function (response) {
          console.log(response);
          const data = response.data;
          console.log(data);
          console.log(data.answer);
          document.getElementById("output").value = String(data.answer);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>

<style>
h1 {
  padding: 0.6em;
  color: #ebebeb;
  opacity: 90%;
  font-size: 300%;
}
h2 {
  color: #ebebeb;
  opacity: 90%;
}
.inputField {
  background-color: #121212;
  color: wheat;
  height: 30vh;
  width: 50%;
}

.outputField {
  background-color: #121212;
  color: wheat;
  height: 30vh;
  width: 50%;
}

.runButton {
  font-size: 22px;
  border-radius: 5px;
  display: block;
  margin: auto;
  padding: 1em 3em;
  margin-top: 20px;
  margin-bottom: 20px;
  transition: 0.2s;
}

.runButton:hover {
  cursor: pointer;
  background-color: lightgray;
}

a {
  font-size: 14px;
}

body {
  background-color: black;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
