package service.relatorio;

public class RelatorioAcessoTexto extends RelatorioAcesso {

    @Override
    protected String criarCabecalho() {
        return "===== RELATÓRIO DE ACESSOS =====\n\n";
    }

    @Override
    protected String criarRodape() {
        return "\n=================================\n";
    }

    @Override
    protected String formatarLinhaResumo(int totalAcessos, long usuariosUnicos) {
        return "Total de acessos: " + totalAcessos + "\nUsuários únicos: " + usuariosUnicos + "\n\n";
    }

    @Override
    protected String abrirListaUsuarios() {
        return "Acessos por usuário:\n";
    }

    @Override
    protected String fecharListaUsuarios() {
        return "";
    }

    @Override
    protected String formatarLinhaUsuario(String nomeUsuario, long quantidadeAcessos) {
        return "- " + nomeUsuario + ": " + quantidadeAcessos + " acesso(s)\n";
    }
}