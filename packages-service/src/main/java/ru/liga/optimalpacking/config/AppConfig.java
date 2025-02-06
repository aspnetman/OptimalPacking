package ru.liga.optimalpacking.config;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.liga.optimalpacking.packages.deleteparcel.DeleteParcelCommandHandler;
import ru.liga.optimalpacking.packages.deleteparcel.businessrules.CheckParcelExistsBusinessRule;
import ru.liga.optimalpacking.packages.deleteparcel.businessrules.DeleteParcelBusinessRulesChecker;
import ru.liga.optimalpacking.packages.editparcel.EditParcelCommandHandler;
import ru.liga.optimalpacking.packages.editparcel.EditParcelMapper;
import ru.liga.optimalpacking.packages.editparcel.businessrules.EditParcelBusinessRulesChecker;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesBillingService;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.exportpackages.ExportPackagesParcelsRepository;
import ru.liga.optimalpacking.packages.exportpackages.TrucksProvider;
import ru.liga.optimalpacking.packages.getparcel.GetParcelQueryHandler;
import ru.liga.optimalpacking.packages.getparcels.GetParcelsQueryHandler;
import ru.liga.optimalpacking.packages.importpackages.FileParcelsReader;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesBillingService;
import ru.liga.optimalpacking.packages.importpackages.ImportPackagesCommandHandler;
import ru.liga.optimalpacking.packages.importpackages.PackingService;
import ru.liga.optimalpacking.packages.importpackages.TrucksRepository;
import ru.liga.optimalpacking.packages.importpackages.businessrules.CheckFilledTrucksExceededMaxValueBusinessRule;
import ru.liga.optimalpacking.packages.importpackages.businessrules.ImportPackagesBusinessRulesChecker;
import ru.liga.optimalpacking.packages.importpackages.packingalgorithms.DensePacking;
import ru.liga.optimalpacking.packages.importpackages.packingalgorithms.UniformPacking;
import ru.liga.optimalpacking.packages.shared.ParcelsRepository;
import ru.liga.optimalpacking.shared.OutboxMessageRepository;

@Configuration
public class AppConfig {

    @Bean
    Pipeline pipeline(ObjectProvider<Command.Handler> commandHandlers,
                      ObjectProvider<Notification.Handler> notificationHandlers,
                      ObjectProvider<Command.Middleware> middlewares) {
        return new Pipelinr()
                .with(commandHandlers::stream)
                .with(notificationHandlers::stream)
                .with(middlewares::orderedStream);
    }

    @Bean
    public FileParcelsReader fileParcelsReader() {
        return new FileParcelsReader();
    }

    @Bean
    public CheckParcelExistsBusinessRule checkParcelExistsBusinessRule(
            ParcelsRepository parcelsRepository) {
        return new CheckParcelExistsBusinessRule(parcelsRepository);
    }

    @Bean
    public DeleteParcelBusinessRulesChecker deleteParcelBusinessRulesChecker(
            CheckParcelExistsBusinessRule checkParcelExistsBusinessRule) {
        return new DeleteParcelBusinessRulesChecker(checkParcelExistsBusinessRule);
    }

    @Bean
    public DeleteParcelCommandHandler deleteParcelCommandHandler(
            ParcelsRepository parcelsRepository,
            DeleteParcelBusinessRulesChecker deleteParcelBusinessRulesChecker) {
        return new DeleteParcelCommandHandler(parcelsRepository, deleteParcelBusinessRulesChecker);
    }

    @Bean
    public ru.liga.optimalpacking.packages.editparcel.businessrules.CheckParcelExistsBusinessRule checkParcelExistsForEditBusinessRule(
            ParcelsRepository parcelsRepository) {
        return new ru.liga.optimalpacking.packages.editparcel.businessrules.CheckParcelExistsBusinessRule(parcelsRepository);
    }

    @Bean
    public EditParcelBusinessRulesChecker editParcelBusinessRulesChecker(
            ru.liga.optimalpacking.packages.editparcel.businessrules.CheckParcelExistsBusinessRule checkParcelExistsBusinessRule) {
        return new EditParcelBusinessRulesChecker(checkParcelExistsBusinessRule);
    }

    @Bean
    public EditParcelMapper editParcelMapper() {
        return Mappers.getMapper(EditParcelMapper.class);
    }

    @Bean
    public EditParcelCommandHandler editParcelCommandHandler(
            ParcelsRepository parcelsRepository,
            EditParcelBusinessRulesChecker editParcelBusinessRulesChecker,
            EditParcelMapper mapper) {
        return new EditParcelCommandHandler(parcelsRepository, editParcelBusinessRulesChecker, mapper);
    }

    @Bean
    public TrucksProvider trucksProvider() {
        return new TrucksProvider();
    }

    @Bean
    public ExportPackagesParcelsRepository exportPackagesParcelsRepository() {
        return new ExportPackagesParcelsRepository();
    }

    @Bean
    public ExportPackagesBillingService exportPackagesBillingService(
            OutboxMessageRepository outboxMessageRepository) {
        return new ExportPackagesBillingService(
                outboxMessageRepository);
    }

    @Bean
    public ExportPackagesCommandHandler exportPackagesCommandHandler(
            TrucksProvider trucksProvider,
            ExportPackagesParcelsRepository exportPackagesParcelsRepository,
            ExportPackagesBillingService exportPackagesBillingService) {
        return new ExportPackagesCommandHandler(
                trucksProvider,
                exportPackagesParcelsRepository,
                exportPackagesBillingService);
    }

    @Bean
    public GetParcelQueryHandler getParcelQueryHandler(
            ParcelsRepository parcelsRepository) {
        return new GetParcelQueryHandler(parcelsRepository);
    }

    @Bean
    public GetParcelsQueryHandler getParcelsQueryHandler(
            ParcelsRepository parcelsRepository) {
        return new GetParcelsQueryHandler(parcelsRepository);
    }

    @Bean
    public TrucksRepository trucksRepository() {
        return new TrucksRepository();
    }

    @Bean
    public CheckFilledTrucksExceededMaxValueBusinessRule checkFilledTrucksExceededMaxValueBusinessRule() {
        return new CheckFilledTrucksExceededMaxValueBusinessRule();
    }

    @Bean
    public ImportPackagesBusinessRulesChecker importPackagesBusinessRulesChecker(
            CheckFilledTrucksExceededMaxValueBusinessRule checkFilledTrucksExceededMaxValueBusinessRule) {
        return new ImportPackagesBusinessRulesChecker(checkFilledTrucksExceededMaxValueBusinessRule);
    }

    @Bean
    public DensePacking densePacking() {
        return new DensePacking();
    }

    @Bean
    public UniformPacking uniformPacking() {
        return new UniformPacking();
    }

    @Bean
    public PackingService packingService(
            DensePacking densePacking,
            UniformPacking uniformPacking) {
        return new PackingService(densePacking, uniformPacking);
    }

    @Bean
    public ImportPackagesBillingService importPackagesBillingService(
            OutboxMessageRepository outboxMessageRepository) {
        return new ImportPackagesBillingService(
                outboxMessageRepository);
    }

    @Bean
    public ImportPackagesCommandHandler importPackagesCommandHandler(
            TrucksRepository trucksRepository,
            ImportPackagesBusinessRulesChecker importPackagesBusinessRulesChecker,
            PackingService packingService,
            FileParcelsReader fileParcelsReader,
            ImportPackagesBillingService importPackagesBillingService) {
        return new ImportPackagesCommandHandler(
                trucksRepository,
                importPackagesBusinessRulesChecker,
                packingService,
                fileParcelsReader,
                importPackagesBillingService);
    }
}
