var query = getQuery()

confirmar('Confirmar operação','Todos o logs serão exlcuídos.\n' +
    'Essa operação não poderá ser desfeita.\n Deseja continuar?',1)

query.update('DELETE AD_SMTLIT')

query.close();

mensagem = 'Logs excluídos com sucesso!'