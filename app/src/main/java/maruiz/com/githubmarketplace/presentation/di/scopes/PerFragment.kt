package maruiz.com.githubmarketplace.presentation.di.scopes

import javax.inject.Scope

/**
 * Annotation for Dagger component that is scoped to an Fragment.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment