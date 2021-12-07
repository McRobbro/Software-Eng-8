<template id="app-frame">
  <div class="app-frame">
    <main>
      <section>
        <slot></slot>
      </section>
    </main>
  </div>
</template>
<script>
app.component("app-frame", {template: "#app-frame"});
</script>
<style>

:root {
  --gold-color: #FFD700;
  --background-color: #1e1e1e;
}

body {
  font-size: 1em;
  min-height: 100%;
  background-color: var(--background-color);
  display: block;

  font-family: 'Montserrat', sans-serif;
}

main {
  color: white;
  margin: 2% auto;
  min-width: 1000px;
  width: 95%;
  height: 100%;
  padding: 2.5em 0 2.5em 0;
}

main section {
  margin: auto;
  height: 100%;
  width: 92.5%;
  background-color: var(--background-color);
}

main section p{
  font-size: 1em;
  float: left;
}

button {
  position: relative;
  margin: 15px 0;
  padding: 5px;
  left: 50%;
  transform: translate(-50%)
}

ul.store-overview-list a {
  display: block;
  max-width: 250px;

  padding: 16px;
  background: #0e0e0e;
  text-decoration: none;
  color: white;

  border-bottom: 1px solid var(--gold-color);
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
}
h1 {
  text-align: center;
}
.itemblock {
  position: absolute;
  left: 50%;
  transform: translate(-50%);
  display: block;
  width: 33%;

  padding: 16px;
  margin: auto;
  background: #0e0e0e;
  font-size: 1.25em;
  text-decoration: none;
  color: white;

  border-bottom: 1px solid var(--gold-color);
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
}
.itemblock-form {
  display: flex;
  flex-flow: column;
}
</style>