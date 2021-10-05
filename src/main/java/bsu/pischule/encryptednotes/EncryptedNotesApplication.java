package bsu.pischule.encryptednotes;

import bsu.pischule.encryptednotes.entity.Note;
import bsu.pischule.encryptednotes.entity.User;
import bsu.pischule.encryptednotes.repository.NoteRepository;
import bsu.pischule.encryptednotes.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Slf4j
@SpringBootApplication
@AllArgsConstructor
public class EncryptedNotesApplication {
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    public static void main(String[] args) {
        SpringApplication.run(EncryptedNotesApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            List<String> texts = List.of(
                    "Ребята, не стоит вскрывать эту тему. Вы молодые, шутливые, вам все легко. Это не то. Это не Чикатило и даже не архивы спецслужб. Сюда лучше не лезть. Серьезно, любой из вас будет жалеть. Лучше закройте тему и забудьте, что тут писалось. Я вполне понимаю, что данным сообщением вызову дополнительный интерес, но хочу сразу предостеречь пытливых - стоп. Остальные просто не найдут.",
                    "Написал hello world и калькулятор, — вот и молодец. На этом стоп. Не стоит вскрывать эти конпеляторы и гитхабы. Это тебе не колидоры вычистлительных центров НАСА, даже не датацентры ГУГОЛ, не уютненькие офисы ФЕЙСБУКА. В сферу IT лучше не лезть. Серьезно, любой из вас будет жалеть. Лучше закройте Хабрахабр и забудьте, что тут писалось. Это все вранье, чтобы привлечь как можно больше новых макак на рабочие места и создать демпинг зарплат. Я вполне понимаю, что данным сообщением вызову дополнительный интерес у воротил из Cisco, SAP и IBM, но хочу сразу предостеречь пытливых — стоп. Зарплаты у IT-шников очень унылые. Остальным их просто не дают.",
                    "Ребята не стоит вскрывать эти теги. Вы уже не молодые, усталые, вам не так легко. Это не то. Это не сайты на Wordpress и даже не покупные ссылки. Сюда лучше не лезть. Серьезно, любой из вас будет жалеть. Лучше закройте биржу копирайта и забудьте что тут продавалось. Я вполне понимаю что данным сообщением вызову дополнительный интерес, но хочу сразу предостеречь пытливых - стоп. Остальные просто не заработают.",
                    "Ученые не стоит ставить в антимасс-спектрометр этот кристалл. Вы молодые, доктора наук, вам вся квантовая физика как дважды два четыре. Это не то. Это не Большой адронный коллайдер и даже не эксперименты по телепортации эсминца Элдридж и ледокола Борей. В нестабильные порталы с пограничным миром Зен лучше не лезть. Серьезно, в случае каскадного резонанса любой из вас будет жалеть. Лучше остановите эксперимент и замените этот нестабильный образец на обычный. Я вполне понимаю что данным сообщением вызову дополнительный интерес к пространственным аномалиям в инопланетных кристаллах, но хочу сразу предостеречь пытливых - стойте! Остальные просто не устроят апокалипсис.",
                    "Ребята, не стоит вкладываться в этот хайп. Вы молодые, интернет-предприниматели, вам все легко. Это не то. Это не Форекс и даже не биржа сомнительных криптовалют. Сюда лучше не лезть. Серьезно, любой из вас будет жалеть. Лучше вложитесь банк или в проверенный бизнес и забудьте, что тут писалось про сотни процентов в месяц. Я вполне понимаю, что данным сообщением вызову дополнительный интерес, но хочу сразу предостеречь пытливых - стоп. Остальных просто не разведут.",
                    "Ребята, не стоит покупать эту шаурму. Вы молодые, здоровые, вам все легко. Это не то. Это не шашлык и даже не сендвичи из макдака. Это лучше не есть. Серьезно, любой из вас будет жалеть. Лучше поешьте фрукты и забудьте, что тут продавалось. Я вполне понимаю, что данным сообщением вызову дополнительный интерес, но хочу сразу предостеречь пытливых - не портите свой желудок. Остальные просто не найдут.",
                    "Сотрудники, не стоит рассекречивать эти объекты. Вы молодые, вам всё легко. Это не Кетер и даже не Таумиэль. Сюда лучше не лезть. Совет О5 может узнать об этом. Лучше закройте тему и забудьте, что тут писалось. Я вполне понимаю, что данным сообщением вызову дополнительный интерес, но хочу сразу предостеречь пытливых - стоп. Остальные просто не найдут.",
                    "Сегодня распечатка этого треда легла на стол очень важному человеку. Администрация ресурса подконтрольна, потому все ваши данные уже есть. Вам дают несколько дней, после чего делу дадут ход. Подумайте если не о себе, то о ваших близких. Потом будет поздно. Лучше остановитесь и закройте обсуждение."
            );
            for (int i = 0; i < texts.size(); ++i) {
                Note note = noteRepository.save(new Note(i + 1, texts.get(i)));
                log.info("Note saved: {}", note);
            }

            User user = new User(1L, null, null, null, null);
            userRepository.save(user);
            log.info("User saved: {}", user);
        };
    }

}
