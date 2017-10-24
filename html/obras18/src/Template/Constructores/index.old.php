<h1>Artículos</h1>
<table>
    <tr>
        <th>Id</th>
        <th>NIF</th>
    </tr>

    <!-- Aquí es donde iteramos nuestro objeto de consulta $articles, mostrando en pantalla la información del artículo -->

    <?php foreach ($constructores as $cons): ?>
    <tr>
        <td><?= $cons->idConstructor ?></td>
        <td>
            <?= $this->Html->link($cons->Usuario_NIF,
            ['controller' => 'Constructores', 'action' => 'view', $cons->idConstructor]) ?>
        </td>
    </tr>
    <?php endforeach; ?>
</table>
