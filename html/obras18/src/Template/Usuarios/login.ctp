<div class="cont">
    <div class="login-box">
        <div class="box-header">
            <h2>Acceso</h2>
        </div>
        <?= $this->Flash->render() ?>
        <?= $this->Form->create();?>
        <label for="username">Usuario</label>
        <br/>
        <input name='NIF' type="text" id="username">
        <br/>
        <label for="password">Contraseña</label>
        <br/>
        <input name='contraseña' type="password" id="password">
        <br/>
        <button type="submit">Enviar</button>
        <br/>
        <?= $this->Form->end();?>
        <a href="#"><p class="small">¿Ha olvidado su contraseña?</p></a>
    </div>
</div>