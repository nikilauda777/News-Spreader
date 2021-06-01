# 30.02.2020
Observer

 Observer
----------------------------------------------



Small news-broadcasting application.
Trusted news sources can spread news to an arbitrary number of registered components.


- 1 broadcasting component (an implementation of Subject [1])
- n news-sources
- m news-receivers (implementations of Observer [1])



```
/**
 * An interface for spreading news.
 */
public interface NewsSpreader {
	
	/**
	 * Registers a trusted news-source.
	 * 
	 * @param source a string used to identify the source
	 * @param pwd    a password that allows to authenticate the source when
	 *               spreading news
	 * @return false if source is null or already registered or if pwd is null or empty , true otherwise
	 */
	public boolean registerTrustedSource(String source, String pwd);

	/**
	 * 
	 * @param news   a string that contains the news to be spread
	 * @param source the source of the news (which must be already registered)
	 * @param pwd    the password (must match the registered password for this source)
	 * @throws UntrustedSourceException when the source was not registered before
	 * @throws AuthenticationException  when the source was registered with a different password
	 */
	public void spreadNews(String news, String source, String pwd)
			throws UntrustedSourceException, AuthenticationException;

}
```

 One broadcasting component that allows components to register as trusted source and spread news to other components.

 At least one component that acts as a news-source (can be as simple as the command-line asking for your news input).

 At least two components that receive news. Received news can be printed, for example, in a file or the console. 

By using the Observer pattern, the involved components only require minimal knowledge about each other.


