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
  margin: 80px auto;
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
  float: left;
}

</style>