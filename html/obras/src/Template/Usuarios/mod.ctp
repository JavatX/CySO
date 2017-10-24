
<div class="contentinfo">
<?php if ($isAuth): ?>
	<?= $this->Flash->render() ?>
	<div class="informacion">
		<?php date_default_timezone_set('UTC');?>
		<?= $this->Flash->render() ?>
		<?= $this->Form->create();?>
		<p>
			<?php echo $this->Form->label('Usuarios.nombre', 'Nombre:');?>
			<br>
			 <?php echo $this->Form->text('nombre', ['value' => $usuario->nombre]); ?>
		</p>
		<p>
			<?php echo $this->Form->label('Usuarios.apellidos', 'Apellidos:');?>
			<br>
			<?php echo $this->Form->text('apellidos', ['value' => $usuario->apellidos]); ?>
		</p>
		<p>
			<?php echo $this->Form->label('Usuarios.contrasena', 'Contraseña:');?>
			<br>
			<?php echo $this->Form->password('contrasena'); ?>
		</p>
		<p>
			<?php echo $this->Form->label('Usuarios.contrasena', 'Repita la contraseña:');?>
			<br>
			<?php echo $this->Form->password('contrasena2'); ?>
		</p>
		<p>
			<?php echo $this->Form->label('Usuarios.NIF', 'NIF:');?>
			<br>
			<?php echo $this->Form->text('NIF', ['value' => $usuario->NIF, 'id' => 'username']); ?>
		</p>
		<div>
			<?= $this->Form->submit('Enviar'); ?>
		</div>
		<?= $this->Form->end();?>
	</div>
	<?php else: ?>
	<?php echo "No tiene permisos para acceder a esta sección"; ?>
<?php endif; ?>
</div>


