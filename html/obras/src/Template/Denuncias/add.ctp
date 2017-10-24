<!--
<div class="contentinfo">
	<div class="informacion">
		Denuncia para la construcción: <?php echo $this->viewVars['cons']?>
		<?php date_default_timezone_set('UTC');?>
		<form method="post" accept-charset="utf-8" action="/html/obras/denuncias/add">
			<input type="hidden" name="fecha" value="<?php echo date('Y-m-d')?>">
			<input type="hidden" name="numDenuncia" value="<?php echo date('dmy_Gys'); echo $this->viewVars['cons'] ?>">
			<input type="hidden" name="construcciones_idConstruccion" value="<?php echo $this->viewVars['cons']?>">
			<p>
				Seleccione una fotografía:<br>
				<input type="file" name="foto" size="40">
			</p>
			<p>
				Introduzca una descripción breve:<br>
				<textarea name="descripcion" cols="50" rows="5"></textarea>
			</p>
			<div>
				<input type="submit" value="Enviar">
			</div>
		</form>
		 <?php echo $this->Form->create($denuncia); ?>
	</div>
</div>
-->
<div class="contentinfo">
	<div class="informacion">
		Denuncia para la construcción: <?php echo $this->viewVars['cons']?>
		<?php 
		date_default_timezone_set('UTC');
		$date = date("Y-m-d");
		$date = date("Y-m-d",strtotime(str_replace('/','-',$date)));
		echo $date;
		echo $this->Form->create($denuncia, ['type' => 'file']); 
		echo $this->Form->hidden('numDenuncia', ['value' => date('dmy_Gys') . $this->viewVars['cons']]);
		echo $this->Form->hidden('fecha', ['value' => $date]);
		echo $this->Form->hidden('construcciones_idConstruccion', ['value' => $this->viewVars['cons']]);?>
			<p>
				Seleccione una fotografía:<br>
				<?php echo $this->Form->file('foto'); ?>
			</p>
			<p>
				Introduzca una descripción breve:<br>
				<?php echo $this->Form->textarea('descripcion', ['rows' => '5', 'cols' => '50']);?>
			</p>
			<div>
				<?= $this->Form->button('Enviar'); ?>
			</div>
		<?= $this->Form->end(); ?>
	</div>
</div>