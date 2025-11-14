package pt.rvcoding.personalwebsitecomposehtml.models.content

import pt.rvcoding.personalwebsitecomposehtml.models.BULLET
import pt.rvcoding.personalwebsitecomposehtml.models.ContentType.BoldText
import pt.rvcoding.personalwebsitecomposehtml.models.ContentType.IndentedText
import pt.rvcoding.personalwebsitecomposehtml.models.ContentType.Paragraph
import pt.rvcoding.personalwebsitecomposehtml.models.ContentType.Text
import pt.rvcoding.personalwebsitecomposehtml.models.PersonalContent


object ContentData {
    object Profile {
        val main by lazy {
            Profile(
                name = "Rui Vieira",
                profession = "Android Engineer",
                description = mainDescription,
                email = "revss87@gmail.com",
                linkedin = "https://www.linkedin.com/in/revs87",
                github = "https://github.com/revs87",
                stackoverflow = "https://stackoverflow.com/users/2996699/edgar-v",
                twitterX = "https://x.com/revs87",
                instagram = "https://www.instagram.com/revs87"
            )
        }
        private val mainDescription = listOf(
            PersonalContent(Text,
                "With over a decade of experience, " +
                        "I am offering a comprehensive expertise in software development, spanning from conceptualization to delivery. " +

                        "Within the Android mobile infrastructure, " +
                        "I advocate for quality by adhering to clean coding principles, " +
                        "through the implementation of team standards, testing models, " +
                        "thorough peer review, " +
                        "pair programming and by spending enough time for self-development and side projects. " +

                        "I aspire to secure a tech lead position while recurring to a specialized developer role that promises both short- and long-term impact. " +

                        "Here are the enduring themes that continually drive my professional pursuits:"
            ),
            PersonalContent(IndentedText, "$BULLET Designing and executing comprehensive, end-to-end features;"),
            PersonalContent(IndentedText, "$BULLET Proactively identifying and addressing challenges in alignment with the company's strategy and vision;"),
            PersonalContent(IndentedText, "$BULLET Conducting thorough reviews, maintaining, and scaling the existing code base;"),
            PersonalContent(IndentedText, "$BULLET Providing guidance through mentoring, effectively delegating tasks, and meticulously documenting processes."),
        )
    }

    object AboutMe {
        val main by lazy {
            AboutMe(
                title = "About Me",
                description = description1,
                description2 = description2
            )
        }
        private val description1 = listOf(
            PersonalContent(Text, "I am Rui Vieira, a Portuguese national male from the Metropolitan Porto area."),
            PersonalContent(Paragraph),
            PersonalContent(Text, "After achieving 2 journal publications with a scholarship as a Researcher, I asked myself: How do I reach people with my code? The quick answer was obviously - Mobile devices."),
            PersonalContent(Paragraph),
            PersonalContent(Text, "First employment was in Porto city centre as a Consultant that converted later to an Android Developer."),
            PersonalContent(Paragraph),
            PersonalContent(Text, "Stayed for 2 years, until I fell to an old ambition of mine of meeting other work cultures. I resigned and spent a week in London. Received a paid trip for an interview in Sheffield and nailed the contract."),
            PersonalContent(Paragraph),
            PersonalContent(Text, "Outside of the comfort zone, I lived in Sheffield for 2 years. For family reasons, I then returned to Portugal with a Remote Worker deal proposed by the CTO himself (this was in 2018)."),
            PersonalContent(Paragraph),
            PersonalContent(Text, "We’ve ended this partnership in 2023 due to the company's lack of revenue thus entering the layoff scheme."),
            PersonalContent(Paragraph),
            PersonalContent(Text, "Started immediately as an Android Lead on B2B projects and here is where I'm professionally bounded."),
        )

        private val description2 = listOf(
            PersonalContent(Text,
                "With more than ten years of experience, I’ve learned this is a race of learning. If we fall behind we will struggle on advancing with development and decision making.\n" +
                        "Mobile development is fast changing. To keep up with the new APIs thus find out what the best new practices are, is a must!\n"
            ),
            PersonalContent(Paragraph),
            PersonalContent(Text,
                "So we have to train!\n" +
                        "For that, I work on side projects to experiment new things and deliver at least a ‘look-a-like’ final product. " +
                        "The content in my GitHub account emboss the power of Kotlin, Jetpack Compose, Presentation Patterns as MVVM and MVI, Clean architecture, Local persistency (SavedStateHandle, DataStorePreferences, Room), Coroutines, Flows, Dependency injection (Hilt, Koin or Repository singleton pattern), Http-client (Retrofit and Ktor) or Http-server (Ktor) and applied principles of SOLID."
            ),
            PersonalContent(Paragraph),
            PersonalContent(Text,
                "In addition, I’ve indulged myself with some set of certifications, which is a common practice of mine to keep learning also on an online courses basis."
            ),
            PersonalContent(Paragraph),
            PersonalContent(Text,
                "And I can say the feeling of accomplishment in professional or side projects is what continues to drive me into software development."
            ),
        )
    }

    object PortfolioCVNotes {
        val main by lazy {
            Portfolio(
                title = "CVNotes",
                subTitle = "Android Native App",
                subSubTitle = "Highlights: Clean Architecture, Firebase Auth",
                description = emptyList(),
            )
        }
        val content = listOf(
            PersonalContent(Text, "A snappy and customizable CV editor."),
            PersonalContent(Paragraph),
            PersonalContent(Text, "Contributed to self improvements of the following topics:"),
            PersonalContent(IndentedText, "$BULLET Latest Google Play deployment restrictions;"),
            PersonalContent(IndentedText, "$BULLET Clean Architecture application;"),
            PersonalContent(IndentedText, "$BULLET Firebase Auth application;"),
            PersonalContent(Paragraph),
            PersonalContent(Text, "More info: [[Github Link][https://github.com/revs87/cvnotes-and]]"),
        )
    }

    object HistorySensormatic {
        val main by lazy {
            History(
                title = "Sensormatic by Johnson Controls",
                subTitle = "Android Lead  @Porto, PT",
                period = "February 2023 – now",
                description = emptyList(),
            )
        }
        val content1 by lazy {
            History(
                title = "",
                subTitle = "Contributions",
                period = "",
                description = listOf(
                    PersonalContent(Text, "Within a DDD architecture, I run B2B Android apps and libraries influent to the INDITEX group retail flow (Retail sector – Zara, Bershka, " +
                            "Pull&Bear, Massimo Dutti, Oysho, Stradivarius, Lefties and Zara Home) embracing +40k devices all around the world."),
                    PersonalContent(IndentedText, "$BULLET Physical tech used: Barcode readers, Active (Sleds) RSSI/RFID readers and passive RFID circuits."),
                    PersonalContent(IndentedText, "$BULLET Hard tech used: Gradle, Github, Hilt, Koin, Jetpack Compose, TDD, Performance, INDITEX external libraries integration and management."),
                    PersonalContent(IndentedText, "$BULLET Side perks: Android lead, critical/side-thinking awareness, tech debt evaluation and orchestration, passing the value to others by " +
                            "lecturing. Responsible for product ownership, board updates and new starter interviews."),
                    PersonalContent(IndentedText, "$BULLET Collaboration frameworks and tools: GitHub, DevHub, Azure, AppCenter, JFrog, Grafana, AppInsights, Jira, Agile Kanban."),
                ),
            )
        }
        val content2 by lazy {
            History(
                title = "PickingSINT",
                subTitle = "Android B2B App",
                period = "",
                description = listOf(
                    PersonalContent(Text, "PickingSINT is a tool designed for store colleagues who do SINT (\"Integrated Stock\") picking. PickingSINT is a key store application to improve store work productivity.")
                ),
            )
        }
        val content3 by lazy {
            History(
                title = "Matching",
                subTitle = "Android B2B App",
                period = "",
                description = listOf(
                    PersonalContent(Text, "The need arose for an application to streamline the process of matching picked clothing items with their corresponding orders, thus expediting the de-alarming and packaging stages."),
                    PersonalContent(Text, "Previously, picking was done on a per-order basis, leading to increased time spent navigating the warehouse (organized by sections and sub-families)."),
                    PersonalContent(Text, "This new picking process was proposed, allowing clothes from all orders to be collected in the same sequence they were stored in the warehouse."),
                    PersonalContent(Text, "This enabled \"matching\" or pairing items with their orders after collection, optimizing the entire process."),
                    PersonalContent(Text, "The tool aimed to address this need by providing an interface to sort completed Picking orders, facilitating item-to-order matching and simplifying subsequent de-alarming and packaging tasks."),
                ),
            )
        }
        private const val SENSORMATIC_LINK_FILE1 = "/raw/20230500_Mejoras_en_la_arquitectura_Android_v1.0.pdf"
        val content4 by lazy {
            History(
                title = "Quality Assessment",
                subTitle = "First Impressions",
                period = "",
                description = listOf(
                    PersonalContent(Text, "This is a technical document about Android architecture and Best practices used in Android development."),
                    PersonalContent(Text, "I used this opportunity to gain knowledge about the state and contents of the applications I was going to work and own upon arrival."),
                    PersonalContent(Text, "I presented a PDF detailing my analysis, pinpointing areas of concern, potential red flags, highlighting areas for improvement, and actionable recommendations:"),
                    PersonalContent(IndentedText, "$BULLET [[20230500_Mejoras_en_la_arquitectura_Android_v1.0.pdf][$SENSORMATIC_LINK_FILE1]]"),
                ),
            )
        }
    }

    object HistoryTheFloow {
        val main by lazy {
            History(
                title = "The Floow",
                subTitle = "Senior Android Developer  @Sheffield, UK",
                period = "May 2016 – February 2023",
                description = emptyList(),
            )
        }
        val content1 by lazy {
            History(
                title = "",
                subTitle = "Contributions",
                period = "",
                description = listOf(
                    PersonalContent(Text, "Worked on Android apps for evaluating vehicle driving behaviour (using bespoke activity recognition) and engaging a driving score that " +
                            "enables appealing insurance discounts (Insurance sector)."),
                    PersonalContent(IndentedText, "$BULLET Established concrete know how into full app development based on clean architecture layers (UI – Domain – Data) and based on a set " +
                            "of MVVM rules for connecting Business Logic and Data to the UI. Also considered coding principles such as Separation of concerns, " +
                            "abstraction layers, dependency inversion for Repository and UseCase layers, and testing inclusion (JUnit, Mockito, Cucumber, Gherkin)."),
                    PersonalContent(IndentedText, "$BULLET Deepened my knowledge in Kotlin: its data mangling power, coroutines and flows."),
                    PersonalContent(IndentedText, "$BULLET Fully on board with Jetpack Compose and ViewModel integration (and w/ or w/o Material)."),
                    PersonalContent(IndentedText, "$BULLET Role of problem finder to procure short term impact: analysed and identified weaknesses around app's background service against" +
                            "multiple device brands and multiple Android versions."),
                    PersonalContent(IndentedText, "(source: [[https://dontkillmyapp.com][https://dontkillmyapp.com/]])"),
                    PersonalContent(IndentedText, "$BULLET Shell and Python scripting: for multiple AARs flattening and to exploit automation under a CI/CD custom integration."),
                    PersonalContent(IndentedText, "$BULLET Collaboration frameworks and tools: Firebase, Jira, Agile Scrum, Git, Bitbucket, Bamboo, Jenkins, Miro, ZeroHeight, Crowdin, ZenDesk."),
                )
            )
        }
    }

    object HistoryITSector {
        val main by lazy {
            History(
                title = "IT sector",
                subTitle = "Android Developer  @Porto, PT",
                period = "October 2013 – April 2016",
                description = emptyList(),
            )
        }
        val content1 by lazy {
            History(
                title = "",
                subTitle = "Contributions",
                period = "",
                description = listOf(
                    PersonalContent(Text, "As a seasoned Android Developer in the Fintech sector, I designed, developed, debugged, modified, and deployed banking applications that reached thousands of users across Africa and Portugal. I led a productive small team in the successful creation of Android apps."),
                    PersonalContent(Paragraph),
                    PersonalContent(Text, "I was proficient in modeling RESTful web services to ensure seamless data exchange and provide offline data availability. I also developed controllers and components from scratch, demonstrating my ability to create robust and efficient solutions."),
                    PersonalContent(Paragraph),
                    PersonalContent(Text, "My experience also included working as an outsourced in partnership with Bank Millennium in Warsaw, Poland. In this role, I handled end to end UI development, from data fetching to UI population, ensuring a smooth user experience."),
                    PersonalContent(Paragraph),
                    PersonalContent(Text, "In my Android development experience, one of my most notable achievements was collaborating with Millennium BIM from Mozambique. I implemented effective code reuse strategies, leveraging inheritance, base classes, and utility classes to minimize redundancy and enhance maintainability. Additionally, I managed keystore security, created engaging canvas animations, and oversaw the entire app deployment process, ensuring smooth transitions from alpha and beta testing to a successful production launch."),
                    PersonalContent(Paragraph),
                    PersonalContent(Text, "In the end, I fostered a collaborative work environment, providing mentorship and continuous assistance to fellow developers in resolving issues and enhancing their development skills."),
                )
            )
        }
    }

    object HistoryINESC {
        val main by lazy {
            History(
                title = "INESCTec",
                subTitle = "Researcher  @Porto, PT",
                period = "November 2011 – August 2013",
                description = mainDescription,
            )
        }
        private val mainDescription = listOf(
            PersonalContent(Text, "At INESCTec, I streamlined research processes for concurrency and implicit parallelism, contributing to the scientific community with documented proof of guided execution hypothesis thus performance enhancements. " +
                    "I collaborated effectively with my leadership partners to pinpoint the challenges and define the development of key optimal strategies to address with. " +
                    "Through in-depth research on new implicit strategies, I expanded my knowledge base on how to tackle different execution trees with different implicit parallelism strategies. " +
                    "Additionally, multiple scenario data analysis allowed me to interpret complex results, drawing meaningful conclusions that provided valuable data and further research directions in the scientific community."),
        )
        val content1 by lazy {
            History(
                title = "Publications",
                subTitle = "2012-DAMP",
                period = "January 2012",
                description = listOf(
                    PersonalContent(BoldText, "Or-Parallel Prolog Execution on Multicores Based on Stack Splitting"),
                    PersonalContent(Text, "[[2012-DAMP][https://dl.acm.org/doi/abs/10.1145/2103736.2103738]]"),
                    PersonalContent(Text, "" +
                            "Many or-parallel Prolog computational models exploiting implicit parallelism have been proposed in the past. The Muse and YapOr systems are arguably two of the most efficient systems exploiting or-parallelism on shared memory architectures, both based on the environment copying model. Stack splitting emerged as an alternative model specially geared to distributed memory architectures as it basically splits the computation in such a way that no further, or just minimal, synchronization is required.\n" +
                            "With the new multicore architectures, it just makes sense to recover the body of knowledge there is in this area and reengineer prior computational models to evaluate their performance on newer architectures. In this paper, we focus on the design and implementation of stack splitting in the YapOr system. Our aim is to take advantage of its robustness to efficiently implement stack splitting support using shared memory, and then be able to directly compare the original YapOr with the YapOr using stack splitting. We consider two splitting models, vertical splitting and half splitting, and have adapted data structures, scheduling and incremental copy procedures in YapOr to cope with the new models. Experimental results, on a multicore machine with 24 cores, show that YapOr using stack splitting is, in general, comparable to the original YapOr, obtaining in some cases better performance than with only environment copying."),
                ),
            )
        }
        val content2 by lazy {
            History(
                title = "",
                subTitle = "2012-CICLOPS",
                period = "September 2012",
                description = listOf(
                    PersonalContent(BoldText, "On Comparing Alternative Splitting Strategies for Or-Parallel Prolog Execution on Multicores"),
                    PersonalContent(Text, "[[2012-CICLOPS][https://arxiv.org/abs/1301.7690]]"),
                    PersonalContent(Text, "" +
                            "Many or-parallel Prolog models exploiting implicit parallelism have been proposed in the past. Arguably, one of the most successful models is environment copying for shared memory architectures. With the increasing availability and popularity of multicore architectures, it makes sense to recover the body of knowledge there is in this area and re-engineer prior computational models to evaluate their performance on newer architectures. In this work, we focus on the implementation of splitting strategies for or-parallel Prolog execution on multicores and, for that, we develop a framework, on top of the YapOr system, that integrates and supports five alternative splitting strategies. Our implementation shares the underlying execution environment and most of the data structures used to implement or-parallelism in YapOr. In particular, we took advantage of YapOr's infrastructure for incremental copying and scheduling support, which we used with minimal modifications. We thus argue that all these common support features allow us to make a first and fair comparison between these five alternative splitting strategies and, therefore, better understand their advantages and weaknesses."),
                ),
            )
        }
    }

    object HistoryFCUP {
        val main by lazy {
            History(
                title = "Faculty of Sciences - University of Porto",
                subTitle = "Student",
                period = "September 2005 – November 2011",
                description = mainDescription,
            )
        }
        private val mainDescription = listOf(
            PersonalContent(Text, "" +
                    "At Faculty of Sciences (Computer Science Department), I not only established a solid foundation in programming fundamentals but also became proficient in a diverse range of languages, including Bash, C, Java, JS, Python, HTML, PHP, MySQL, Perl, Haskell, and Prolog. I honed my skills in compilers, distributed systems, concurrency, networks, and servers.\n" +
                    "These achievements culminated in my completion of an MSc in Networks and Information Systems Engineering, with a focus on Communication Networks (major) and Distributed Systems (minor). My thesis research in implicit parallelism further solidified my expertise in this field.\n" +
                    "Additionally, my academic performance was recognized with a scholarship, highlighting my dedication and success throughout my studies and further publications @INESCTec."),
        )
        val content01 by lazy {
            History(
                title = "MSc Thesis",
                subTitle = "",
                period = "November 2011",
                description = listOf(
                    PersonalContent(BoldText, "Or-Parallel Prolog Execution on Multicores Based on Stack Splitting"),
                    PersonalContent(Text, "[[THESIS][https://www.dcc.fc.up.pt/~ricroc/homepage/alumni/2011-vieiraMSc.html]]"),
                    PersonalContent(Text, "Prolog is a popular logic programming language that provides a declarative approach to programming, being thus highly amenable for implicit parallelism. There are many efficient sequential implementations of Prolog, mostly based on the Warren Abstract Machine (WAM). Prolog is currently much used by machine learning and natural language practitioners, but its applicability is much wider in scope.\n" +
                            "Implicit parallel implementations of Prolog have been proposed in the past. The Muse and YapOr systems are arguably two of the most efficient systems for shared memory architectures, both based on the environment copying model. Stack splitting emerged as an alternative model specially geared to distributed shared memory architectures as it basically splits the computation in such a way that no further, or just minimal, synchronization is required."),
                )
            )
        }
        val content02 by lazy {
            History(
                title = "",
                subTitle = "",
                period = "",
                description = listOf(
                    PersonalContent(Text,
                            "With the new multicore architectures, it just makes sense to recover the body of knowledge there is in this area and either devise newer computational models that fit best recent parallel architectures, or to reengineer prior computational models to evaluate their performance on newer architectures. Here, we take the second path.\n" +
                            "In this thesis, we focus on the design and implementation of the stack splitting strategy in the YapOr system. Our aim is to take advantage of its robustness to efficiently implement stack splitting support using shared memory, and then be able to directly compare the YapOr based on environment copying with the YapOr based on stack splitting. We devised two splitting schemes, the vertical splitting and the half splitting, and have adapted data structures, scheduling and incremental copying procedures in YapOr to cope with the new schemes. Finally, we evaluate their performance on a set of known benchmarks on a multicore machine with up to 24 cores. Our initial results confirm that YapOr with the stack splitting schemes is, in general, comparable to YapOr with environment copying, obtaining in some cases better performance than with environment copying."),
                )
            )
        }
    }
}