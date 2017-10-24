<div class="contentinfo">
	<div class="informacion">
		<?php date_default_timezone_set('UTC');
		echo $this->Form->create($construccion, ['type' => 'file']); 
		/*echo $this->Form->hidden('numDenuncia', ['value' => date('dmy_Gys') . $this->viewVars['cons']]);
		echo $this->Form->hidden('fecha', ['value' => date('Y-m-d')]);
		echo $this->Form->hidden('construcciones_idConstruccion', ['value' => $this->viewVars['cons']]);*/?>
			<p>
				<?php 
				echo $this->Form->label('idConstruccion', 'ID Construcción:');
				?><br><?php
				echo $this->Form->text('idConstruccion'); ?>
			</p>
			<p>
				<?php 
				echo $this->Form->label('direccion', 'Direccion:');
				?><br><?php
				echo $this->Form->text('direccion'); ?>
			</p>
			<p>
				<?php 
				echo $this->Form->label('superficie', 'Superficie (en m2):');
				?><br><?php
				echo $this->Form->text('superficie'); ?>
			</p>
			<p>
				<?php echo $this->Form->label('descripcion', 'Descripción:');?><br>
				<?php echo $this->Form->textarea('descripcion', ['rows' => '5', 'cols' => '50']);?>
			</p>
			<p>
				<?php echo $this->Form->label('Constructor_Usuario_NIF', 'Constructor:');?><br>
				<?php echo $this->Form->select('Constructor_Usuario_NIF', $arrayNIF);?>
			</p>
			<div>
				<?= $this->Form->button('Enviar'); ?>
			</div>
		<?= $this->Form->end(); ?>
	</div>
</div>