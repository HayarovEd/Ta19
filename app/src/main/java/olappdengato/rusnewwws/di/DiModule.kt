package olappdengato.rusnewwws.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import olappdengato.rusnewwws.data.repository.RemoteRepositoryImpl
import olappdengato.rusnewwws.domain.repository.RemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DiModule {
    @Binds
    @Singleton
    abstract fun bindRepository(taRepository: RemoteRepositoryImpl): RemoteRepository
}