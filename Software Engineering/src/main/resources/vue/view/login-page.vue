<template id="login-page">
  <navbar>
  </navbar>
  <app-frame>
    <div class="itemblock">
      <form class="itemblock-form" :action=`/api/login` method="post">
        <label for="role-select">Velg brukertype </label>
        <select name="role" id="role-select">
          <option>Uregistrert bruker</option>
          <option>Registrert bruker</option>
          <option>Butikkeier</option>
          <option>Administrator</option>
        </select>
        <button type="submit">Logg inn</button>
      </form>
    </div>
  </app-frame>
</template>
<script>


app.component("login-page", {
  template: "#login-page",
});
</script>
<style>

</style>