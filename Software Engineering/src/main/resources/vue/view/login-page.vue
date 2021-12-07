<template id="login-page">
  <navbar>
  </navbar>
  <app-frame>
    <div class="itemblock">
      <form class="itemblock-form" :action=`/api/login` method="post">
        <label for="role-select">Select role</label>
        <select name="role" id="role-select">
          <option>ANYONE</option>
          <option>STORE_OWNER</option>
          <option>PLATFORM_OWNER</option>
          <option>USER</option>
        </select>
        <button type="submit">Log in</button>
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