
<div class="contentinfo">
<?php if ($isAuth): ?>
	<div class="informacion">
		<?php date_default_timezone_set('UTC');?>
		<?= $this->Flash->render() ?>
		<?= $this->Form->create();?>
		<p>
			Usuario:<br>
			<input type="text" name="nombre">
		</p>
		<p>
			Apellidos:<br>
			<input type="text" name="apellidos">
		</p>
		<p>
			Contraseña:<br>
			<input type="password" name="contraseña" id="password">
		</p>
		<p>
			NIF:<br>
			<input type="text" name="NIF" id="username">
		</p>
		<p>
			Tipo:<br>
			<select id="tipo" name="tipo">
 				<option value="" selected="selected">- selecciona -</option>
				<option value="admin">Administrador</option>
				<option value="inspector">Inspector</option>
				<option value="cons">Constructor</option>
			</select>
		</p>
		<div>
			<input type="submit" value="Enviar">
		</div>
		<?= $this->Form->end();?>
		<?php echo $this->Form->create($usuarios); ?>
	</div>
	<?php else: ?>
	<?php echo "No tiene permisos para acceder a esta sección"; ?>
<?php endif; ?>
</div>


