package service.relatorio;

import model.RegistroAcesso;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class RelatorioAcesso {

    public final String gerar(List<RegistroAcesso> registros) {
        StringBuilder sb = new StringBuilder();
        sb.append(criarCabecalho());
        sb.append(formatarResumoGeral(registros));
        sb.append(formatarAcessosPorUsuario(registros));
        sb.append(criarRodape());
        return sb.toString();
    }

    private String formatarResumoGeral(List<RegistroAcesso> registros) {
        int total = registros.size();
        long usuariosUnicos = registros.stream()
                .map(RegistroAcesso::getUsuarioId)
                .distinct()
                .count();
        return formatarLinhaResumo(total, usuariosUnicos);
    }

    private String formatarAcessosPorUsuario(List<RegistroAcesso> registros) {
        Map<String, Long> contagem = registros.stream()
                .collect(Collectors.groupingBy(RegistroAcesso::getNomeUsuario, Collectors.counting()));

        StringBuilder sb = new StringBuilder();
        sb.append(abrirListaUsuarios());
        for (Map.Entry<String, Long> entry : contagem.entrySet()) {
            sb.append(formatarLinhaUsuario(entry.getKey(), entry.getValue()));
        }
        sb.append(fecharListaUsuarios());
        return sb.toString();
    }

    protected abstract String criarCabecalho();
    protected abstract String criarRodape();
    protected abstract String formatarLinhaResumo(int totalAcessos, long usuariosUnicos);
    protected abstract String abrirListaUsuarios();
    protected abstract String fecharListaUsuarios();
    protected abstract String formatarLinhaUsuario(String nomeUsuario, long quantidadeAcessos);
}